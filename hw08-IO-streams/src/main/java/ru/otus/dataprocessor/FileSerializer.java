package ru.otus.dataprocessor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        // формирует результирующий json и сохраняет его в файл
        try (var writer = new FileWriter(fileName)) {
            CustomGson.getInstance().toJson(data, writer);
        } catch (IOException e) {
            throw new FileProcessException(e.getMessage());
        }
    }
}
