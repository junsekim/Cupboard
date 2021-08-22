package com.icemelon404.cupboard.reader.impl.factory;

import com.icemelon404.cupboard.annotations.Component;
import com.icemelon404.cupboard.annotations.Policy;
import com.icemelon404.cupboard.bean.Bean;
import com.icemelon404.cupboard.bean.impl.BeanCreationPolicy;
import com.icemelon404.cupboard.bean.impl.ObjectFactory;
import com.icemelon404.cupboard.bean.impl.SimpleBean;
import com.icemelon404.cupboard.bean.impl.policy.SingletonPolicy;
import com.icemelon404.cupboard.exception.BeanCreationFailException;
import com.icemelon404.cupboard.reader.impl.AnnotatedBeanFactory;

import java.lang.annotation.Annotation;

public class SimpleBeanFactory implements AnnotatedBeanFactory {

    @Override
    public Bean createBean(ObjectFactory factory, Annotation[] annotations) {
        return new SimpleBean(factory, getPolicy(annotations), getName(annotations, factory.getType()));
    }

    private BeanCreationPolicy getPolicy(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Policy.class)) {
                Policy policy = (Policy) annotation;
                return policy.value().build();
            }
        }
        return new SingletonPolicy();
    }

    private String getName(Annotation[] annotations, Class<?> type) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Component.class)) {
                Component component = (Component) annotation;
                String name = component.name();
                if (name.isEmpty())
                    break;
                return name;
            }
        }
        return type.getSimpleName();
    }
}
