package com.zkq.utils;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
public class WebContextListener implements ServletContextListener {
    public WebContextListener(){
        super();
        log.debug("{[]} initialize start...", WebContextListener.class.getSimpleName());
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        getInitializeLogo();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
    private void getInitializeLogo() {
            log.info("/***\n" +
                    " *  .--,       .--,\n" +
                    " * ( (  \\.---./  ) )\n" +
                    " *  '.__/o   o\\__.'\n" +
                    " *     {=  ^  =}\n" +
                    " *      >  -  <\n" +
                    " *     /       \\\n" +
                    " *    //       \\\\\n" +
                    " *   //|   .   |\\\\\n" +
                    " *   \"'\\       /'\"_.\n" +
                    " *      \\  _  /--'         `\n" +
                    " *    ___)( )(___\n" +
                    " *   (((__) (__)))\n" +
                    " *...........................*\n"+
                    " *   高山仰止,景行行止,\n" +
                    " *   虽不能至,心向往之。\n" +
                    " */\n");
    }
}

