package com.gupaoedu.spring.formework.context;

import com.gupaoedu.spring.formework.beans.GPBeanFactory;
import com.gupaoedu.spring.formework.beans.GPBeanWrapper;
import com.gupaoedu.spring.formework.beans.config.GPBeanDefinition;
import com.gupaoedu.spring.formework.beans.support.GPBeanDefinitionReader;
import com.gupaoedu.spring.formework.beans.support.GPDefaultListableBeanFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GPApplicationContext extends GPDefaultListableBeanFactory implements GPBeanFactory {

    private String[] configLocation;

    private GPBeanDefinitionReader reader;

    //单例的IOC容器
    private Map<String,Object> singletonObjects = new ConcurrentHashMap<String,Object>();

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

    /**
     * 只处理非延迟加载情况
     */
    private void doAutowrited() {

        //遍历beanDefintionMap,判断是否是延迟加载
        for (Map.Entry<String, GPBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if(!beanDefinitionEntry.getValue().isLazyinit()){
                getBean(beanName);
            }
        }

    }

    private void doRegisterBeanDefintion(List<GPBeanDefinition> beanDefintions) {
        //放入到Map中
        for(GPBeanDefinition beanDefinition : beanDefintions){
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
    }

    @Override
    public Object getBean(String beanName) {
        /**
         *  为什么会分两步,这里是防止循环注入
         *  class A{B b};
         *  class B{A a};
         */
        //1.初始化
        GPBeanWrapper beanWrapper = instantiateBean(beanName,new GPBeanDefinition());

        //2.拿到BeanWrapper之后保存到IOC容器中

        //3.注入
        populateBean(beanName,new GPBeanDefinition(),beanWrapper);
        return null;
    }

    private void populateBean(String beanName, GPBeanDefinition gpBeanDefinition, GPBeanWrapper beanWrapper) {
    }

    private GPBeanWrapper instantiateBean(String beanName, GPBeanDefinition beanDefinition) {
        //1.拿到实例化对象得类名
        String className = beanDefinition.getBeanClassName();
        //2.反射进行初始化
        Object instance = null;
        try {
            if(this.singletonObjects.containsKey(className)){
                instance = this.singletonObjects.get(className);
            }else{
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.singletonObjects.put(className,instance);
                this.singletonObjects.put(beanName,instance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.把对象封装到BeanWrapper中
        GPBeanWrapper beanWrapper = new GPBeanWrapper(instance);
        return beanWrapper;
    }
}
