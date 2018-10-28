package com.zkq.Controller;

import com.zkq.domain.UsersCustom;
import com.zkq.service.userservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class CheckLogin {
    @Autowired
    userservice userservice;
    @RequestMapping("/login")
    public String CheckLogin(UsersCustom usersCustom){
        log.debug(usersCustom.getUsername()+usersCustom.getPassword());
       boolean flag= userservice.checkUserNameAndPassword(usersCustom);
            if(flag){
                return "main";
            }else{
                return "";
            }
    }
}
