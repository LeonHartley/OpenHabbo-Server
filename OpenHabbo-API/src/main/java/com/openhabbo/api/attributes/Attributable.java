package com.openhabbo.api.attributes;

public interface Attributable {
    <T> T attribute(AttributeKey<T> key);

    <T> void attribute(AttributeKey<T> key, T value);

    void removeAttribute(AttributeKey key);

    void clearAttributes();
}
