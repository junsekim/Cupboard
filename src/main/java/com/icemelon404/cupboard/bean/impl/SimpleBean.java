package com.icemelon404.cupboard.bean.impl;

import com.icemelon404.cupboard.bean.Bean;
import com.icemelon404.cupboard.bean.BeanProfile;
import com.icemelon404.cupboard.bean.BeanSource;
import com.icemelon404.cupboard.commons.ReflectionUtils;

public class SimpleBean implements Bean {

    private ObjectFactory objectFactory;
    private BeanCreationPolicy createPolicy;
    private String beanName;

    public SimpleBean(ObjectFactory objectFactory, BeanCreationPolicy createPolicy, String beanName) {
        this.objectFactory = objectFactory;
        this.createPolicy = createPolicy;
    }

    @Override
    public Object requestBean(BeanSource dependencySource) {
        return createPolicy.requestBean(dependencySource, objectFactory);
    }

    @Override
    public boolean matches(BeanProfile profile) {
        Class<?> profileType = profile.getClassType();
        Class<?> beanType = objectFactory.getType();
        if (!ReflectionUtils.isEqualOrSuperTypeOf(profileType, beanType))
            return false;
        if (profile.hasName() && !beanName.equals(profile.getBeanName()))
            return false;
        return true;
    }
}
