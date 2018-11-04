package com.zkq.Interceptor;

import com.zkq.domain.UsersCustom;
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

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String requestUrl=request.getRequestURI(); //获得请求的URL
        log.debug("请求的URL:"+requestUrl);
        //获取session
        HttpSession session=request.getSession();
        //得到登录用户的相关信息
        UsersCustom usersCustom=new UsersCustom();
        usersCustom.setUsername(request.getParameter("username"));
        usersCustom.setPassword(request.getParameter("password"));
        //判断是否存在cookie,存在cookie拦截，直接进入后台管理页面，免登录
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&cookies.length!=0){
            for(Cookie cookie:cookies){
                System.out.println(cookies.length);
                System.out.println(cookie.getName()+ "....00000000000000000000000000000000000....." +cookie.getValue());
                if(Objects.equals(cookie.getName(),"www.zkq.cn")){
                    flag=true;
                }
                break;
            }
        }
        //此时cookie存在，进行拦截并且存储转发到主页面，设置session
        if(flag){
            if(requestUrl.indexOf("/login.action")>0){
                log.debug("存在此cookie，拦截免登录");
                session.setAttribute("username",usersCustom);
                request.getRequestDispatcher("/toMain.action").forward(request,response);
                return false;
            }
            return  true;
        }else{
            //不存在cookie，即不能直接进入后台管理页面，对登录进行拦截
            log.debug("不存在cookie");
            //判断请求的URL是否为公开地址，是则不拦截
            if(uncheckUrls.contains(requestUrl)){
                log.debug("请求地址为公开地址，不拦截");
                return true;
            }
            if(session.getAttribute("username")!=null){
                log.debug("用户在session中存在，放行");
                return  true;
            }else{
                log.debug("session中不存在用户，拦截");
                request.getRequestDispatcher("login.html").forward(request,response);
                return false;
            }
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
