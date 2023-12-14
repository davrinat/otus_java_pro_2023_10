package ru.otus.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.service.TestLogging;
import ru.otus.service.TestLoggingImpl;
import ru.otus.utils.CustomChecker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Ioc {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    private Ioc() {}

    public static TestLogging createClass() {
        InvocationHandler handler = new MyInvocationHandler(new TestLoggingImpl());
        return (TestLogging)
                Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[] {TestLogging.class}, handler);
    }

    static class MyInvocationHandler implements InvocationHandler{
        private final TestLogging test;
        Method[] methods;

        public MyInvocationHandler(TestLogging test) {
            this.test = test;
            methods = test.getClass().getDeclaredMethods();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isNeedLog(method)) {
                logger.info("execute method: " + method.getName() + ", param " + Arrays.toString(args));
            }
            return method.invoke(test, args);
        }

        private boolean isNeedLog(Method method) {
            return CustomChecker.check(test, method, methods);
        }
    }
}
