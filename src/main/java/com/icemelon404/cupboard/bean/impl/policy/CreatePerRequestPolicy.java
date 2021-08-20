package com.icemelon404.cupboard.bean.impl.policy;

import com.icemelon404.cupboard.bean.BeanSource;
import com.icemelon404.cupboard.bean.impl.BeanCreationPolicy;
import com.icemelon404.cupboard.bean.impl.ObjectFactory;

public class CreatePerRequestPolicy implements BeanCreationPolicy {
    @Override
    public Object requestBean(BeanSource dependencySource, ObjectFactory factory) {
        return factory.createObject(dependencySource);
    }
}
