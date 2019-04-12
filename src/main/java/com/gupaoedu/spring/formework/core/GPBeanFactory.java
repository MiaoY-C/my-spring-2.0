package com.gupaoedu.spring.formework.core;

/**
 * Created with IntelliJ IDEA.
 * User: MiaoYongchang
 * Date: 2019/4/12
 * Time: 13:26
 * Description: No Description
 */
public interface GPBeanFactory {
    /**
     * 根据beanName从IOC容器中获得一个bean实例
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}
