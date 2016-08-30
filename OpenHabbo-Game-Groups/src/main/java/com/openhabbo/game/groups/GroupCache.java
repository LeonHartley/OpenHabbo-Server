package com.openhabbo.game.groups;

import com.google.inject.Inject;
import com.netflix.governator.annotations.Configuration;
import com.openhabbo.api.threading.EventScheduler;
import com.openhabbo.core.caching.types.BasicLifetimeCache;
import com.openhabbo.game.groups.types.GroupInstance;

public class GroupCache extends BasicLifetimeCache<Integer, GroupInstance> {

    @Configuration("openhabbo.cache.groupObjectLifetime")
    private long groupObjectLifetime = 30;

    @Inject
    public GroupCache(EventScheduler eventScheduler) {
        super(eventScheduler);
    }

    @Override
    public long getObjectLifetime() {
        return groupObjectLifetime;
    }
}
