package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.tests.CustomAssertionTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    static Logger log = LoggerFactory.getLogger(App.class);
    static int success = 0;
    static int failure = 0;

    public static void main(String[] args) {
        run();
    }

    private static void run() {

        try {
            Runner.callBefore(CustomAssertionTest.class);
            Runner.callTest(CustomAssertionTest.class);
            Runner.callAfter(CustomAssertionTest.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        log.info("success -> " + success);
        log.error("failure -> " + failure);

        int total = success + failure;
        log.info("Total methods called -> " + total);
    }

    public static class Runner {
        public static void callBefore(Class<CustomAssertionTest> testClass) throws Exception {
            for (Method method : testClass.getDeclaredMethods()) {
                checkAnnotations(method);
                if (method.isAnnotationPresent(Before.class)) {
                    method.setAccessible(true);
                    getInvoke(method);
                }
            }
        }

        public static void callTest(Class<CustomAssertionTest> testClass) {
            for (Method method : testClass.getDeclaredMethods()) {
                checkAnnotations(method);
                if (method.isAnnotationPresent(Test.class)) {
                    method.setAccessible(true);
                    try {
                        getInvoke(method);
                        success++;
                    } catch (Exception e) {
                        failure++;
                        log.error(String.valueOf(e.getCause()));
                    }
                }
            }
        }

        public static void callAfter(Class<CustomAssertionTest> testClass) throws Exception {
            for (Method method : testClass.getDeclaredMethods()) {
                checkAnnotations(method);
                if (method.isAnnotationPresent(After.class)) {
                    method.setAccessible(true);
                    getInvoke(method);
                }
            }
        }

        private static void getInvoke(Method method)
                throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            method.invoke(((Class<?>) CustomAssertionTest.class).getDeclaredConstructor().newInstance());
        }
    }

    private static void checkAnnotations(Method method) {
        for (Annotation annotation : method.getDeclaredAnnotations()) {
            if (annotation.annotationType().isAnnotationPresent(After.class)
                    || annotation.annotationType().isAnnotationPresent(Test.class)) {
                throw new RuntimeException("Don`t use more then one annotation");
            }
        }
    }
}
