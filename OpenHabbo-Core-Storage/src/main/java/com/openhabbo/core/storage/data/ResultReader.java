package com.openhabbo.core.storage.data;

public interface ResultReader extends AutoCloseable {
    String getString(String key);

    Boolean getBoolean(String key);

    Integer getInteger(String key);

    Long getLong(String key);

    Double getDouble(String key);

    Boolean hasResults() throws Exception;
}
