package com.icemelon404.cupboard.bean.impl.policy;

import com.icemelon404.cupboard.bean.impl.BeanCreationPolicy;

public enum CreationPolicyType {
    CREATE_PER_REQUEST {
        @Override
        public BeanCreationPolicy build() {
            return new CreatePerRequestPolicy();
        }
    }, SINGLETON {
        @Override
        public BeanCreationPolicy build() {
            return new SingletonPolicy();
        }
    };

    public abstract BeanCreationPolicy build();
}
