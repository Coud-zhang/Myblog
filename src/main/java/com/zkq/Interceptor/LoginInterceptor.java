package com.zkq.Interceptor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Data
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private List<String> uncheckUrls;
    private Boolean flag=false;
    private String userName;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl=request.getRequestURI(); //获得请求的URL
        log.debug("请求的URL:"+requestUrl);
        //获取session
        HttpSession session=request.getSession();
        //从session中得到登录用户的相关信息
            //判断是否存在cookie,存在cookie拦截，直接进入后台管理页面，免登录
        log.debug("session.getAttribute:"+(String) session.getAttribute("userName"));
            Cookie[] cookies = request.getCookies();
            if(cookies!=null&&cookies.length!=0){
                System.out.println("cookies的长度:"+cookies.length);
                for(Cookie cookie:cookies){
                    System.out.println(cookie.getName()+ ".........." +cookie.getValue());
                    System.out.println("cookie是否相等"+Objects.equals(cookie.getName(),"www.zkq.cn"));
                    if(Objects.equals(cookie.getName(),"www.zkq.cn")){
                        System.out.println("判断相等");
                        flag=true;
                        userName=cookie.getValue();
                        break;
                    }
                }
            }
        System.out.println("判断cookie的标志位flag的值:"+flag);
            //此时cookie存在，进行拦截并且存储转发到主页面，设置session
            if(flag){
                if(requestUrl.contains("/toLoginView.action")){
                    log.debug("请求toLogin且存在cookie，拦截请求免登录");
                    session.setAttribute("userName",userName);
                    response.sendRedirect("/toMain.action");
                    return false;
                }
                //cookie存在，但不是请求tologin页面
                if(requestUrl.contains("/logout.action")){
                   flag=false;
                }
                return  true;
            }else{
                log.debug("不存在cookie");   // 没有勾选记住密码，或者退出时清楚了cookie
                if(uncheckUrls.contains(requestUrl)){
                    log.debug("公开地址");
                    return true;
                }
                if(session.getAttribute("username")!=null){
                    log.debug("用户在session中存在，放行");
                    return  true;
                }else{
                    log.debug("session中不存在用户，拦截");
                    response.sendRedirect("/toLoginView.action");
                    return false;
                }
            }

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
