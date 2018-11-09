package com.zkq.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class Dispatcher {

    @RequestMapping("/toMain")
    public String dispatcherToMain(){
        log.debug("跳转到main.html");
        return "main.html";
    }
    @RequestMapping("/toView")
    public String dispatcherToview(){
        log.debug("跳转到view.html");
        return "view.html";
    }
    @RequestMapping("/aboutme")
    public  String aboutMe(){
        log.debug("跳转到关于我.html");
        return "about.html";
    }
    @RequestMapping("/toLoginView")
    public String tologin(){
        return "login.html";
    }

    @RequestMapping("/toblog")
    public String toBlog(){
        return "BlogView.jsp";
    }
}
