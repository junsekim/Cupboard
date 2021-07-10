package com.icemelon404.cupboard.reader.impl.scanner;

import com.icemelon404.cupboard.commons.ReflectionUtils;
import com.icemelon404.cupboard.reader.impl.ClassScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

public class AnnotatedClassScanner implements ClassScanner {

    private List<String> targetPackages;
    private Class<? extends Annotation> annotationClass;
    private Logger logger = LoggerFactory.getLogger(AnnotatedClassScanner.class);

    public AnnotatedClassScanner(List<String> packages, Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
        this.targetPackages = packages;
    }

    @Override
    public List<Class<?>> findClasses() {
        List<Class<?>> foundClasses = new LinkedList<>();
        for (String packagePath : targetPackages) {
            try {
                Class<?>[] classes = ReflectionUtils.getClasses(packagePath);
                for (Class<?> check : classes) {
                    if (check.getAnnotation(annotationClass) != null)
                        foundClasses.add(check);
                }
            } catch (ClassNotFoundException | IOException e) {
                logger.warn("Error while scanning components in package: {}, skipping package", packagePath);
            }
        }
        return foundClasses;
    }
}
