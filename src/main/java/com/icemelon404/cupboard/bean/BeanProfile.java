package com.icemelon404.cupboard.bean;

public class BeanProfile {
    private Class<?> classType;
    private String beanName;
    private boolean hasName;

    public BeanProfile(Class<?> classType, String beanName) {
        this.classType = classType;
        this.beanName = beanName;
        this.hasName = true;
    }

    public BeanProfile(Class<?> classType) {
        this.classType = classType;
        this.hasName = false;
        beanName = "";
    }

    public Class<?> getClassType() {
        return classType;
    }

    public String getBeanName() {
        return beanName;
    }

    public boolean hasName() {
        return hasName;
    }
}
