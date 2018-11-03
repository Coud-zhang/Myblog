package com.zkq.Controller;

import com.zkq.domain.UsersCustom;
import com.zkq.service.userservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginAndLogout {
    @Autowired
    userservice userservice;
    @RequestMapping("/login")
    @ResponseBody
    public String CheckLogin(UsersCustom usersCustom, HttpSession session, @RequestParam("remberMe") boolean ifcheck){
        log.debug("username:"+usersCustom.getUsername()+"password:"+usersCustom.getPassword());
        boolean flag= userservice.checkUserNameAndPassword(usersCustom);
        if(flag){
                session.setAttribute("username",usersCustom);
            return "true";
        }else{
            return "false";
        }
    }
    @RequestMapping("/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "view";
    }
}
