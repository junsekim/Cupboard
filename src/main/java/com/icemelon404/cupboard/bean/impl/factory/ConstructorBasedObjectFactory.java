package com.icemelon404.cupboard.bean.impl.factory;

import com.icemelon404.cupboard.bean.BeanSource;
import com.icemelon404.cupboard.bean.impl.ObjectFactory;
import com.icemelon404.cupboard.exception.BeanCreationFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Constructor;

public class ConstructorBasedObjectFactory implements ObjectFactory {

    private final Class<?> classType;
    private final Logger logger = LoggerFactory.getLogger(ConstructorBasedObjectFactory.class);

    public ConstructorBasedObjectFactory(Class<?> classType) {
        this.classType = classType;
    }

    @Override
    public Object createObject(BeanSource source) {
        Constructor<?>[] constructors = classType.getConstructors();
        for (Constructor<?> constructor : constructors) {
            Object obj = callConstructor(source, constructor);
            if (obj != null)
                return obj;
        }
        throw new BeanCreationFailException("Cannot find proper constructor");
    }

    private Object callConstructor(BeanSource beanSource, Constructor<?> constructor) {
        Class<?>[] params = constructor.getParameterTypes();
        Object[] paramObjects = new Object[constructor.getParameterCount()];
        int idx = 0;
        try {
            for (Class<?> paramType : params) {
                paramObjects[idx] = beanSource.requestBean(paramType);
                idx++;
            }
            return constructor.newInstance(paramObjects);
        } catch (Exception e) {
            logger.debug("Exception while creating object", e);
            return null;
        }
    }

    @Override
    public Class<?> getType() {
        return classType;
    }
}
