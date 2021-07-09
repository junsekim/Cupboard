package com.icemelon404.cupboard.bean.impl;

import com.icemelon404.cupboard.bean.BeanSource;

public interface BeanCreationPolicy {
    Object requestBean(BeanSource dependencySource, ObjectFactory factory);
}
