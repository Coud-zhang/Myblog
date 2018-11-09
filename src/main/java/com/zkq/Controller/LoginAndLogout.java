package com.zkq.Controller;

import com.zkq.domain.UsersCustom;
import com.zkq.service.userservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginAndLogout {
    @Autowired
    userservice userservice;
    @RequestMapping("/login")
    @ResponseBody
    public String checkLogin(HttpServletResponse response,UsersCustom usersCustom, HttpSession session, @RequestParam(name="remberMe",required =false) boolean remberme){
        log.debug("username:"+usersCustom.getUsername()+".."+"password:"+usersCustom.getPassword());
        boolean flag= userservice.checkUserNameAndPassword(usersCustom);
        if(flag){
            if(remberme==true){
                log.debug("勾选了记住密码");
                Cookie cookie=new Cookie("www.zkq.cn",usersCustom.getUsername());
                cookie.setMaxAge(864000);
                response.addCookie(cookie);
            }
            session.setAttribute("userName",usersCustom.getUsername());
            log.debug("用户名密码验证成功");
            return "true";
        }else{
            log.debug("用户名密码验证失败");
            return "false";
        }
    }
    @RequestMapping("/logout")
    public String logOut(HttpSession session,HttpServletResponse response,HttpServletRequest request){
        String userName= (String) request.getSession().getAttribute("userName");
        System.out.println(userName);
        Cookie cookie=new Cookie("www.zkq.cn",userName);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        session.invalidate();
        return "redirect:/toView.action";
    }
}
