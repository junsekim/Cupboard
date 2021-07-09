package com.icemelon404.cupboard.bean;

public interface BeanSource {
    <T> T requestBean(Class<T> beanType);
    <T> T requestBean(Class<T> beanType, String beanName);
}
