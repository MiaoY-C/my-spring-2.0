package com.gupaoedu.spring.formework.beans;

/**
 * Created with IntelliJ IDEA.
 * User: MiaoYongchang
 * Date: 2019/4/12
 * Time: 13:26
 * Description: 单列工厂顶层设计
 */
public interface GPBeanFactory {
    /**
     * 根据beanName从IOC容器中获得一个bean实例
     * @param beanName
     * @return
     */
    Object getBean(String beanName) throws Exception;

    Object getBean(Class<?> clazz) throws Exception;
}
