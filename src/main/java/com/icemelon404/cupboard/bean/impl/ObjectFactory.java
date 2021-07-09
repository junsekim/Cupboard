package com.icemelon404.cupboard.bean.impl;

import com.icemelon404.cupboard.bean.BeanSource;

public interface ObjectFactory {
    Object createObject(BeanSource source);
    Class<?> getType();
}
