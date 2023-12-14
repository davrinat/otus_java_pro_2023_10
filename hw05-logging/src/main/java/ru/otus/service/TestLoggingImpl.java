package ru.otus.service;

import ru.otus.annotations.Log;

public class TestLoggingImpl implements TestLogging{

    @Log
    @Override
    public void calculation(int param1) {}

    @Override
    public void calculation(int param1, int param2) {}

    @Log
    @Override
    public void calculation(int param1, int param2, String param3) {}
}
