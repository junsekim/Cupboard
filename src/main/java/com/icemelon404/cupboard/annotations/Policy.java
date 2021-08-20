package com.icemelon404.cupboard.annotations;

import com.icemelon404.cupboard.bean.impl.policy.CreationPolicyType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Policy {
    CreationPolicyType value() default CreationPolicyType.SINGLETON;
}
