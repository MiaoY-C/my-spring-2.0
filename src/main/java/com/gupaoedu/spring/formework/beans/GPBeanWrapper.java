package com.gupaoedu.spring.formework.beans;

/**
 * Created with IntelliJ IDEA.
 * User: MiaoYongchang
 * Date: 2019/4/17
 * Time: 13:25
 * Description: No Description
 */
public class GPBeanWrapper {
    /**
     * 是单例直接拿到
     * @return
     */
    public Object getWrapperedInstance(){
        return  null;
    }

    /**
     * 不是单例new一个
     * @return
     */
    public Class<?> getWrappedClass(){
        return  null;
    }
}
