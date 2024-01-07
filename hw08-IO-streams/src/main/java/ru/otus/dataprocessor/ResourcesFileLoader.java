package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class ResourcesFileLoader implements Loader {
    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        // читает файл, парсит и возвращает результат
        try (var reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName))))) {
            return List.of(CustomGson.getInstance().fromJson(reader, Measurement[].class));
        } catch (IOException e) {
            throw new FileProcessException(e.getMessage());
        }
    }
}
