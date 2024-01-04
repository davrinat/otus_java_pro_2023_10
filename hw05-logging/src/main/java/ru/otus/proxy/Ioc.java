package ru.otus.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.annotations.Log;
import ru.otus.utils.CustomClassLoader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Ioc {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    private Ioc() {}

    public static Object createClass(Object instance) {
        InvocationHandler handler = new MyInvocationHandler(instance);
        Class<?> clazz = getClazz(instance.getClass().getInterfaces()[0].getName());
        return Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[] {clazz}, handler);
    }

    private static Class<?> getClazz(String simpleName) {
        ClassLoader loader = new CustomClassLoader();
        try {
            return loader.loadClass(simpleName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static class MyInvocationHandler implements InvocationHandler{
        private final Object obj;
        Set<Method> listMethodsForLog;

        public MyInvocationHandler(Object obj) {
            this.obj = obj;
            listMethodsForLog = new HashSet<>();
            isNeedLog();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            listMethodsForLog.stream()
                    .filter(logMethod -> Arrays.equals(logMethod.getParameterTypes(), method.getParameterTypes())
                    && Arrays.equals(logMethod.getName().getBytes(), method.getName().getBytes()))
                    .map(logMethod -> "execute method: " + method.getName() + ", param " + Arrays.toString(args))
                    .forEach(logger::info);
            return method.invoke(obj, args);
        }

        private void isNeedLog() {
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Log.class)) {
                    listMethodsForLog.add(method);
                }
            }
        }
    }
}
