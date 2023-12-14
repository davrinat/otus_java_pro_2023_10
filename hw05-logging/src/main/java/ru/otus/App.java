package ru.otus;

import ru.otus.proxy.Ioc;
import ru.otus.service.TestLogging;

public class App {
    public static void main(String[] args) {
        TestLogging test = Ioc.createClass();
        test.calculation(6);
        test.calculation(9, 2);
        test.calculation(10, 12, "3");
    }
}
