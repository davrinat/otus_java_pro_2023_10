package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.util.Objects;

public class CustomGson {
    private static Gson instance;

    public static synchronized Gson getInstance() {
        if (Objects.isNull(instance)) {
            return new Gson();
        }
        return instance;
    }
}
