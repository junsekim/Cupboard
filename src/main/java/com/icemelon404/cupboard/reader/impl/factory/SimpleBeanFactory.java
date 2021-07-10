package com.icemelon404.cupboard.reader.impl.factory;

import com.icemelon404.cupboard.bean.Bean;
import com.icemelon404.cupboard.bean.impl.ObjectFactory;
import com.icemelon404.cupboard.bean.impl.SimpleBean;
import com.icemelon404.cupboard.bean.impl.policy.SingletonPolicy;
import com.icemelon404.cupboard.reader.impl.AnnotatedBeanFactory;

import java.lang.annotation.Annotation;

public class SimpleBeanFactory implements AnnotatedBeanFactory {

    @Override
    public Bean createBean(ObjectFactory factory, Annotation[] annotations) {
        return new SimpleBean(factory, new SingletonPolicy(), factory.getType().getSimpleName());
    }
}
