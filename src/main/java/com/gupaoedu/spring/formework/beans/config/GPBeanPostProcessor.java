package com.gupaoedu.spring.formework.beans.config;

/**
 * Created with IntelliJ IDEA.
 * User: MiaoYongchang
 * Date: 2019/4/24
 * Time: 13:06
 * Description: 初始化对象时的通知器
 */
public class GPBeanPostProcessor {

    //初始化前调用
    public Object postProcessBeforInitialization(Object bean,String beanName) throws  Exception{
        return bean;
    }

    //初始化后调用
    public Object postProcessAfterInitialization(Object bean,String beanName)throws Exception{
        return bean;
    }
}
