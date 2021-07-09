package com.icemelon404.cupboard.bean.impl.factory;

import com.icemelon404.cupboard.bean.BeanProfile;
import com.icemelon404.cupboard.bean.BeanSource;
import com.icemelon404.cupboard.bean.impl.ObjectFactory;
import com.icemelon404.cupboard.exception.BeanCreationFailException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorBasedObjectFactory implements ObjectFactory {

    private Class<?> classType;

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
                //TODO-add logging;
            }
        }
        throw new BeanCreationFailException("Proper constructor is not found");
    }

    @Override
    public Class<?> getType() {
        return classType;
    }
}
