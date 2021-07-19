package com.icemelon404.cupboard.bean.impl.factory;

import com.icemelon404.cupboard.bean.BeanSource;
import com.icemelon404.cupboard.bean.impl.ObjectFactory;
import com.icemelon404.cupboard.exception.BeanCreationFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorBasedObjectFactory implements ObjectFactory {

    private Class<?> classType;
    private Logger logger = LoggerFactory.getLogger(ConstructorBasedObjectFactory.class);

    public ConstructorBasedObjectFactory(Class<?> classType) {
        this.classType = classType;
    }

    @Override
    public Object createObject(BeanSource source) {
        Constructor<?>[] constructors = classType.getConstructors();
        for (Constructor constructor : constructors) {
            Class<?>[] params = constructor.getParameterTypes();
            Object[] paramObjects = new Object[constructor.getParameterCount()];
            int idx = 0;
            for (Class<?> paramType : params) {
                paramObjects[idx] = source.requestBean(paramType);
                idx++;
            }
            try {
                return constructor.newInstance(paramObjects);
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                logger.error("Error while creating object with constructor. Object type: {}", classType.getSimpleName());
            }
        }
        throw new BeanCreationFailException("Cannot find proper constructor");
    }

    @Override
    public Class<?> getType() {
        return classType;
    }
}
