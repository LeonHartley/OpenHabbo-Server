package com.openhabbo.core.caching.types;

import com.google.inject.Inject;
import com.netflix.governator.annotations.Configuration;
import com.openhabbo.api.threading.EventScheduler;
import com.openhabbo.core.caching.Cache;
import com.openhabbo.core.caching.CacheableObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public abstract class BasicLifetimeCache<I, T extends CacheableObject> extends Cache<I, T> {

    @Configuration("openhabbo.caching.lifetime.interval")
    private long tickInterval = 30;

    private final UUID eventId;
    private final EventScheduler eventScheduler;

    @Inject
    public BasicLifetimeCache(EventScheduler eventScheduler) {
        this.eventScheduler = eventScheduler;
        this.eventId = eventScheduler.scheduleEvent(this, 0, this.tickInterval, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        final List<I> objectsToRemove = new ArrayList<>();

        for (Map.Entry<I, T> object : this.cachedObjects.entrySet()) {
            final boolean requiresRemove = (System.currentTimeMillis() - object.getValue().getLastCachedAccess()) >= (this.getObjectLifetime() * 1000);

            if (requiresRemove) {
                object.getValue().dispose();

                objectsToRemove.add(object.getKey());
            }
        }

        for (I object : objectsToRemove) {
            this.cachedObjects.remove(object);
        }

        objectsToRemove.clear();
    }

    public T get(I id) {
        T obj = super.get(id);

        obj.resetLastCachedAccess();

        return obj;
    }

    public void remove(I id) {
        this.cachedObjects.remove(id);
    }

    public void put(I id, T object) {
        object.resetLastCachedAccess();

        super.put(id, object);
    }


    @Override
    public void dispose() {
        super.dispose();

        this.eventScheduler.stopScheduledEvent(this.eventId);
    }

    public abstract long getObjectLifetime();
}
