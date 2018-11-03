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
        return "main";
    }
    @RequestMapping("/toView")
    public String dispatcherToview(){
        log.debug("跳转到main.html");
        return "view";
    }
    @RequestMapping("/aboutme")
    public  String aboutMe(){
        return "about";
    }
}
