package com.openhabbo.api.attributes;

public class AttributeKey<T> {
    private final String key;

    public AttributeKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
