package com.gupaoedu.spring.formework.beans.config;


import lombok.Data;

@Data
//data自动生成get set 方法
public class GPBeanDefinition {
    private String beanClassName;
    private boolean lazyinit = false;
    private String factoryBeanName;
}
