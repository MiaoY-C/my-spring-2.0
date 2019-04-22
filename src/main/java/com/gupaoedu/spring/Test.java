package com.gupaoedu.spring;

import com.gupaoedu.spring.formework.context.GPApplicationContext;

public class Test {
    public static void main(String[] args) {
     GPApplicationContext context =    new GPApplicationContext("classpath:application.properties");
        try {
            Object o = context.getBean("myAction");
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
