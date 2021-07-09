package com.icemelon404.cupboard.bean.impl.policy;

import com.icemelon404.cupboard.bean.BeanSource;
import com.icemelon404.cupboard.bean.impl.BeanCreationPolicy;
import com.icemelon404.cupboard.bean.impl.ObjectFactory;

public class SingletonPolicy implements BeanCreationPolicy {

    private Object bean;
    @Override
    public Object requestBean(BeanSource dependencySource, ObjectFactory factory) {
        synchronized(this) {
            if (this.bean == null)
                this.bean = factory.createObject(dependencySource);
            return bean;
        }
    }
}
