package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {

    private final EntityClassMetaData<?> entity;
    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entity) {
        this.entity = entity;
    }

    @Override
    public String getSelectAllSql() {
        return String.format("SELECT %S FROM %S", getColumns(entity), entity.getName());
    }

    @Override
    public String getSelectByIdSql() {
        return String.format("SELECT %S FROM %S WHERE %S = ?",
                getColumns(entity),
                entity.getName(),
                entity.getIdField().getName());
    }

    @Override
    public String getInsertSql() {
        return String.format("INSERT INTO %s VALUES (%s)",
                getInsertField(entity),
                entity.getFieldsWithoutId().stream()
                        .map(field -> "?")
                        .collect(Collectors.joining(", ")));
    }

    @Override
    public String getUpdateSql() {
        return String.format("UPDATE %S WHERE Id = ?", getUpdateField(entity));
    }

    private String getColumns(EntityClassMetaData<?> entityClassMetaData) {
        return entityClassMetaData.getAllFields().stream().map(Field::getName).collect(Collectors.joining(", "));
    }

    private String getInsertField(EntityClassMetaData<?> entityClassMetaData) {
        return entityClassMetaData.getName() + "(" +
                entityClassMetaData.getFieldsWithoutId().stream()
                        .map(Field::getName).collect(Collectors.joining(", ")) + ")";
    }

    private String getUpdateField(EntityClassMetaData<?> entityClassMetaData) {
        return entityClassMetaData.getName() + entityClassMetaData.getFieldsWithoutId()
                .stream()
                .map(field -> "SET " + field.getName() + " = ?").collect(Collectors.joining(", "));
    }
}
