package com.openhabbo.core.caching;

import com.google.common.collect.Maps;
import com.openhabbo.api.util.Disposable;

import java.util.Map;

public abstract class Cache<I, T extends CacheableObject> implements Runnable, Disposable {
    protected final Map<I, T> cachedObjects;

    public Cache() {
        this.cachedObjects = Maps.newConcurrentMap();
    }

    public boolean contains(I id) {
        return this.cachedObjects.containsKey(id);
    }

    public T get(I id) {
        return this.cachedObjects.get(id);
    }

    public void remove(I id) {
        this.cachedObjects.remove(id);
    }

    public void put(I id, T object) {
        this.cachedObjects.put(id, object);
    }

    @Override
    public void dispose() {
        for (Map.Entry<I, T> object : this.cachedObjects.entrySet()) {
            object.getValue().dispose();
        }

        this.cachedObjects.clear();
    }
}
