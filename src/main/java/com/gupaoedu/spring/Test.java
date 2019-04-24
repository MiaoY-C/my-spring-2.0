package com.gupaoedu.spring;

import com.gupaoedu.spring.demo.action.MyAction;
import com.gupaoedu.spring.formework.context.GPApplicationContext;

public class Test {
    public static void main(String[] args) {
     GPApplicationContext context =    new GPApplicationContext("classpath:application.properties");
        try {
            Object o = context.getBean("myAction");
            Object o1  = context.getBean(MyAction.class);
            System.out.println(o);
            System.out.println(o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
