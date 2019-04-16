package com.gupaoedu.spring.formework.context;

import com.gupaoedu.spring.formework.beans.GPBeanFactory;
import com.gupaoedu.spring.formework.beans.config.GPBeanDefinition;
import com.gupaoedu.spring.formework.beans.support.GPBeanDefinitionReader;
import com.gupaoedu.spring.formework.beans.support.GPDefaultListableBeanFactory;

import java.util.List;

public class GPApplicationContext extends GPDefaultListableBeanFactory implements GPBeanFactory {

    private String[] configLocation;

    private GPBeanDefinitionReader reader;

    public GPApplicationContext(String... configLocation){
        this.configLocation = configLocation;
        refresh();//初始化时调用
    }


    @Override
    protected void refresh() {
        //1.定位配置文件BeanDefintionReader
        reader = new GPBeanDefinitionReader(this.configLocation);

        //2.加载配置文件，扫描相关的类，把他们封装成BeanDefinition
        List<GPBeanDefinition> beanDefintions = reader.loadBeanDefintions();

        //3.注册、把配置信息放到容器里面(伪IOC容器)
        doRegisterBeanDefintion(beanDefintions);

        //4.把不是延时加载的类，提前初始化
        doAutowrited();
    }

    private void doAutowrited() {
    }

    private void doRegisterBeanDefintion(List<GPBeanDefinition> beanDefintions) {
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }
}
