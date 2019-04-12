package com.gupaoedu.spring.formework.context;

import com.gupaoedu.spring.formework.beans.GPBeanFactory;
import com.gupaoedu.spring.formework.beans.support.GPDefaultListableBeanFactory;

public class GPApplicationContext extends GPDefaultListableBeanFactory implements GPBeanFactory {

    @Override
    protected void refresh() {
        super.refresh();
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }
}
