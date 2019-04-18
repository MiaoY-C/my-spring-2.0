package com.gupaoedu.spring.formework.beans;

/**
 * Created with IntelliJ IDEA.
 * User: MiaoYongchang
 * Date: 2019/4/17
 * Time: 13:25
 * Description: No Description
 */
public class GPBeanWrapper {
    //对象实例
    private Object wrappedInstance;

    private Class<?> wrappedClass;

    public GPBeanWrapper(Object wrappedInstance){
        this.wrappedInstance = wrappedInstance;
    };


    /**
     * 是单例直接拿到
     * @return
     */
    public Object getWrapperedInstance(){
        return  this.wrappedInstance;
    }

    /**
     * 不是单例new一个
     * @return
     */
    public Class<?> getWrappedClass(){
        return  this.wrappedInstance.getClass();
    }
}
