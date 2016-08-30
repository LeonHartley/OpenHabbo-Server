package com.openhabbo.core.caching;

import com.openhabbo.api.util.Disposable;

public abstract class CacheableObject implements Disposable {
    private long lastCachedAccess = System.currentTimeMillis();

    public void resetLastCachedAccess() {
        this.lastCachedAccess = System.currentTimeMillis();
    }

    public long getLastCachedAccess() {
        return this.lastCachedAccess;
    }
}
