package com.openhabbo.api.attributes;

import com.google.common.collect.Maps;

import java.util.Map;

public abstract class AbstractAttributable implements Attributable {

    private final Map<String, Object> attributes = Maps.newConcurrentMap();

    public <T> T attribute(AttributeKey<T> key) {
        return (T) this.attributes.get(key.getKey());
    }

    public <T> void attribute(AttributeKey<T> key, T value) {
        this.attributes.put(key.getKey(), value);
    }

    public void removeAttribute(AttributeKey key) {
        this.attributes.remove(key.getKey());
    }

    public void clearAttributes() {
        this.attributes.clear();
    }
}
