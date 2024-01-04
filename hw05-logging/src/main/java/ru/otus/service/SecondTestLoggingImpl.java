package ru.otus.service;

import ru.otus.annotations.Log;

public class SecondTestLoggingImpl implements TestLogging{

    @Override
    public void calculation(int param1) {}

    @Log
    @Override
    public void calculation(int param1, int param2) {}

    @Override
    public void calculation(int param1, int param2, String param3) {}
}
