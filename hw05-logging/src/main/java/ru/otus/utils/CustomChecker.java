package ru.otus.utils;

import ru.otus.annotations.Log;
import ru.otus.service.TestLogging;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CustomChecker {

    public static boolean check(TestLogging test, Method method, Method[] methods) {
        boolean isAnnotation = false;
        for (Method checkMethod : methods) {
            if (Arrays.equals(checkMethod.getParameterTypes(), method.getParameterTypes())) {
                isAnnotation = hasAnnotation(checkMethod);
            }
        }
        Method[] iMethods = test.getClass().getInterfaces()[0].getDeclaredMethods();
        return isEquals(method, iMethods) && isAnnotation;
    }

    private static boolean isEquals(Method method, Method[] iMethods) {
        for (Method iMethod : iMethods) {
            if (Arrays.equals(iMethod.getParameterTypes(), method.getParameterTypes())) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasAnnotation(Method method) {
        return method.isAnnotationPresent(Log.class);
    }
}
