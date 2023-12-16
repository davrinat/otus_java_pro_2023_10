package ru.otus;

import ru.otus.proxy.Ioc;
import ru.otus.service.SecondTestLoggingImpl;
import ru.otus.service.TestLogging;
import ru.otus.service.FirstTestLoggingImpl;

public class App {
    public static void main(String[] args) {

        TestLogging test1 = (TestLogging) Ioc.createClass(new FirstTestLoggingImpl());
        test1.calculation(6);
        test1.calculation(9, 2);
        test1.calculation(10, 12, "3");

        System.out.println("---------------------------------------------------------");

        TestLogging test2 = (TestLogging) Ioc.createClass(new SecondTestLoggingImpl());
        test2.calculation(16);
        test2.calculation(39, 12);
        test2.calculation(100, 122, "45");
    }
}
