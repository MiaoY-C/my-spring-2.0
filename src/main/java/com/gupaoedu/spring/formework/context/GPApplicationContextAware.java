package com.gupaoedu.spring.formework.context;

/**
 * 通过解耦的方式获得IOC容器的顶层设计
 * 后面将通过一个监听器扫描类
 * 只要实现了此接口，自动调用setApplicationContext(),从而将IOC容器注入到目标类中
 */
public interface GPApplicationContextAware {

    void setApplicationContext(GPApplicationContext applicationContext);
}
