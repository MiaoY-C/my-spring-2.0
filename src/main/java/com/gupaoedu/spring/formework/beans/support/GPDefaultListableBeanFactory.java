package com.gupaoedu.spring.formework.beans.support;

import com.gupaoedu.spring.formework.beans.config.GPBeanDefinition;
import com.gupaoedu.spring.formework.context.GPApplicationContext;
import com.gupaoedu.spring.formework.context.support.GPAbstactApplicationContext;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 实现一些公共的内容
 */
public class GPDefaultListableBeanFactory extends GPAbstactApplicationContext {

    //存储注册信息的beanDefinition
    protected final Map<String, GPBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String,GPBeanDefinition>();


}
