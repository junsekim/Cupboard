package com.icemelon404.cupboard.bean;

import com.icemelon404.cupboard.bean.BeanProfile;
import com.icemelon404.cupboard.bean.BeanSource;

public interface Bean {
    Object requestBean(BeanSource dependencySource);
    boolean matches(BeanProfile profile);
}
