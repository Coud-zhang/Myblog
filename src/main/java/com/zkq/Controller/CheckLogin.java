package com.zkq.Controller;

import com.zkq.domain.UsersCustom;
import com.zkq.service.userservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class CheckLogin {
    @Autowired
    userservice userservice;
    @RequestMapping("/login")
    @ResponseBody
    public String CheckLogin(UsersCustom usersCustom){
        log.debug("username:"+usersCustom.getUsername()+"password:"+usersCustom.getPassword());
        boolean flag= userservice.checkUserNameAndPassword(usersCustom);
        if(flag){
            return "true";
        }else{
            return "false";
        }

    }
}
