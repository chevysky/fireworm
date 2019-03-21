package com.bulu.fireworm.configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class procedureListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("项目开始执行的方法");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("项目结束执行的方法");
    }
}
