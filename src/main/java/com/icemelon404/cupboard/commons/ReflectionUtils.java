package com.icemelon404.cupboard.commons;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

public class ReflectionUtils {

    public static boolean isEqualOrSuperTypeOf(Class parent, Class child) {
        Class<?> superType = child;
        while (superType != null) {
            if (superType.equals(parent))
                return true;
            superType = superType.getSuperclass();
        }
        for (Class superInterface : child.getInterfaces())
            if (superInterface.equals(parent))
                return true;
        return false;
    }

    public static Set<Field> getAllFields(Class type) {
        Set<Field> fields = new HashSet<>();
        Class<?> superType = type;
        while (superType != null) {
            for (Field f : superType.getDeclaredFields()) {
                fields.add(f);
            }
            superType = superType.getSuperclass();
        }
        return fields;
    }

    public static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
