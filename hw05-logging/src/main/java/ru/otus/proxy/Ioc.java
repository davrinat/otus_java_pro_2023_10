package ru.otus.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.utils.CustomChecker;
import ru.otus.utils.CustomClassLoader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

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
        Method[] methods;

        public MyInvocationHandler(Object obj) {
            this.obj = obj;
            methods = obj.getClass().getDeclaredMethods();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isNeedLog(method)) {
                logger.info("execute method: " + method.getName() + ", param " + Arrays.toString(args));
            }
            return method.invoke(obj, args);
        }

        private boolean isNeedLog(Method method) {
            return new CustomChecker().check(method, methods);
        }
    }
}
