package com.icemelon404.cupboard.reader.impl;

import com.icemelon404.cupboard.bean.Bean;
import com.icemelon404.cupboard.bean.impl.factory.ConstructorBasedObjectFactory;
import com.icemelon404.cupboard.reader.BeanReader;

import java.util.LinkedList;
import java.util.List;

public class ComponentScanner implements BeanReader {

    private ClassScanner classScanner;
    private AnnotatedBeanFactory beanFactory;

    public ComponentScanner(ClassScanner classScanner, AnnotatedBeanFactory factory) {
        this.classScanner = classScanner;
        this.beanFactory = factory;
    }

    @Override
    public List<Bean> readBeans() {
        List<Bean> ret = new LinkedList<>();
        List<Class<?>> targetClasses = classScanner.findClasses();
        for (Class<?> target : targetClasses)
            ret.add(beanFactory.createBean(new ConstructorBasedObjectFactory(target), target.getAnnotations()));
        return ret;
    }
}
