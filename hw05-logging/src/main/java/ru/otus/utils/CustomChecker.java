package ru.otus.utils;

import ru.otus.annotations.Log;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CustomChecker {

    public boolean check(Method method, Method[] methods) {
        boolean isAnnotation = false;
        for (Method checkMethod : methods) {
            if (Arrays.equals(checkMethod.getParameterTypes(), method.getParameterTypes())) {
                isAnnotation = hasAnnotation(checkMethod);
            }
        }
        return isAnnotation;
    }

    private static boolean hasAnnotation(Method method) {
        return method.isAnnotationPresent(Log.class);
    }
}
