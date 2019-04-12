package com.gupaoedu.spring.formework.context.support;

/**
 * ioc容器的顶层设计
 */
public abstract class GPAbstactApplicationContext {
    //改成受保护的，只提供子类去重写
    protected void refresh(){}
}
