package com.zkq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author zkq15
 * */
@Controller
@Slf4j
public class Dispatcher {

    @RequestMapping("/toMain")
    public String dispatcherToMain(){
        log.debug("跳转到main.html");
        return "pages/main.html";
    }
    @RequestMapping("/toView")
    public String dispatcherToview(){
        log.debug("跳转到view.html");
        return "pages/view.html";
    }
    @RequestMapping("/aboutme")
    public  String aboutMe(){
        log.debug("跳转到关于我.html");
        return "pages/about.html";
    }
    @RequestMapping("/toLoginView")
    public String tologin(){
        return "pages/login.html";
    }
}
