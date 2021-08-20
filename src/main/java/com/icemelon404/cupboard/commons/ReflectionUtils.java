package com.icemelon404.cupboard.commons;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

public class ReflectionUtils {

    public static boolean isEqualOrSuperTypeOf(Class<?> parent, Class<?> child) {
        return isEqualOrSuperClassOf(parent, child) || isEqualOrSuperInterfaceOf(parent, child);
    }

    private static boolean isEqualOrSuperInterfaceOf(Class<?> parentInterface, Class<?> child) {
        for (Class<?> superInterface : child.getInterfaces())
            if (superInterface.equals(parentInterface))
                return true;
        return false;
    }

    private static boolean isEqualOrSuperClassOf(Class<?> parentClass, Class<?> childClass) {
        Class<?> superType = childClass;
        while (superType != null) {
            if (superType.equals(parentClass))
                return true;
            superType = superType.getSuperclass();
        }
        return false;
    }


    public static Set<Field> getAllFields(Class<?> type) {
        Set<Field> fields = new HashSet<>();
        Class<?> superType = type;
        while (superType != null) {
            Collections.addAll(fields, superType.getDeclaredFields());
            superType = superType.getSuperclass();
        }
        return fields;
    }

    public static Class<?>[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = getPackageDirectories(resources);
        ArrayList<Class<?>> classes = findAllClasses(dirs, packageName);
        return classes.toArray(new Class[classes.size()]);
    }

    private static List<File> getPackageDirectories(Enumeration<URL> resources) {
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        return dirs;
    }

    private static ArrayList<Class<?>> findAllClasses(List<File> rootDirectory,
                                               String packageName) throws ClassNotFoundException {
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : rootDirectory) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class"))
                classes.add(Class.forName(packageName + '.' + extractClassNameFromFileName(file.getName())));
        }
        return classes;
    }

    private static String extractClassNameFromFileName(String fileName) {
        int extensionLength = 6;
        return fileName.substring(0, fileName.length() - extensionLength);
    }
}
