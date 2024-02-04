package ru.otus.jdbc.mapper;

import ru.otus.crm.annotation.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private String name;
    private Field idField;
    private List<Field> fields;
    private List<Field> fieldsWithoutId;
    private Constructor<T> constructor;

    public EntityClassMetaDataImpl(Class<T> clazz) {
        init(clazz);
    }

    private void init(Class<T> clazz) {
        name = clazz.getSimpleName();
        fields = Arrays.stream(clazz.getDeclaredFields()).toList();
        idField = fields.stream()
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow();
        fieldsWithoutId = fields.stream()
                .filter(field -> !(field.isAnnotationPresent(Id.class)))
                .toList();
        try {
            constructor = clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Constructor<T> getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    @Override
    public List<Field> getAllFields() {
        return fields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return fieldsWithoutId;
    }
}
