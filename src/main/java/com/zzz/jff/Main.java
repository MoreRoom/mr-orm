package com.zzz.jff;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auther zhaowei.zhang
 * @since 2018/4/27 11:23
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        App app = (App) applicationContext.getBean("app");
        System.out.println(app.getAppName() + app.getTable());
    }

}
