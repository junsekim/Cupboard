package com.icemelon404.cupboard.reader.impl;

import com.icemelon404.cupboard.bean.Bean;
import com.icemelon404.cupboard.bean.impl.ObjectFactory;

import java.lang.annotation.Annotation;

public interface AnnotatedBeanFactory {
    Bean createBean(ObjectFactory factory, Annotation[] annotations);
}
