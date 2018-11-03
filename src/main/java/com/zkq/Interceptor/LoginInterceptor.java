package com.zkq.Interceptor;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Data
public class LoginInterceptor implements HandlerInterceptor {

    private List<String> uncheckUrls;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl=request.getRequestURI();
        if(uncheckUrls.contains(requestUrl)){
            return true;
        }
        HttpSession session=request.getSession();
        if(session.getAttribute("username")!=null){
            return  true;
        }
         request.getRequestDispatcher("login.html").forward(request,response);
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
