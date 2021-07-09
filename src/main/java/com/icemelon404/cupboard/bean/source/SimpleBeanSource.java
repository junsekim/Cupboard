package com.icemelon404.cupboard.bean.source;

import com.icemelon404.cupboard.exception.BeanNotFoundException;
import com.icemelon404.cupboard.bean.Bean;
import com.icemelon404.cupboard.bean.BeanProfile;
import com.icemelon404.cupboard.bean.BeanSource;
import com.icemelon404.cupboard.reader.BeanReader;

import java.util.LinkedList;
import java.util.List;

public class SimpleBeanSource implements BeanSource {

    private List<Bean> beans = new LinkedList<>();

    public SimpleBeanSource(List<BeanReader> readers) {
        for (BeanReader reader : readers)
            beans.addAll(reader.readBeans());
    }

    public SimpleBeanSource(BeanReader... readers) {
        for (BeanReader reader : readers)
            beans.addAll(reader.readBeans());
    }

    @Override
    public <T> T requestBean(Class<T> beanType) {
        BeanProfile searchProfile = new BeanProfile(beanType);
        return (T) searchBean(searchProfile);
    }

    @Override
    public <T> T requestBean(Class<T> beanType, String beanName) {
        BeanProfile searchProfile = new BeanProfile(beanType, beanName);
        return (T) searchBean(searchProfile);
    }

    private Object searchBean(BeanProfile searchProfile) {
        for (Bean bean : beans) {
            if (bean.matches(searchProfile))
                return bean.requestBean(this);
        }
        throw new BeanNotFoundException("Bean not found with type: "
                + searchProfile.getClassType().getSimpleName() + " and name: " + searchProfile.getBeanName());
    }
}
