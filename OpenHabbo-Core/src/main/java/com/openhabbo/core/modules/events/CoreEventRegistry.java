package com.openhabbo.core.modules.events;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.api.modules.events.SystemEventRegistry;
import com.openhabbo.api.modules.events.Event;
import com.openhabbo.api.modules.events.EventArgs;
import com.openhabbo.api.threading.EventScheduler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

@Singleton
public class CoreEventRegistry implements SystemEventRegistry {
    private final Logger log = LogManager.getLogger(CoreEventRegistry.class);

    private final Map<Class<? extends Event>, List<Event>> listeners;
    private final EventScheduler eventScheduler;

    @Inject
    public CoreEventRegistry(EventScheduler eventScheduler) {
        this.eventScheduler = eventScheduler;
        this.listeners = Maps.newConcurrentMap();
    }

    @Override
    public void registerEvent(Event consumer) {
        if (this.listeners.containsKey(consumer.getClass())) {
            this.listeners.get(consumer.getClass()).add(consumer);
        } else {
            this.listeners.put(consumer.getClass(), Lists.newArrayList(consumer));
        }

        log.debug(String.format("Registered event listener for %s", consumer.getClass().getSimpleName()));
    }

    @Override
    public void unregisterEvent(Class<? extends Event> eventClass) {
        if(!this.listeners.containsKey(eventClass)) {
            return;
        }

        this.listeners.get(eventClass).clear();
    }

    @Override
    public  <T extends EventArgs> void invoke(Class<? extends Event> eventClass, T args) {
        if(!this.listeners.containsKey(eventClass)) {
            return;
        }

        for (Event event : this.listeners.get(eventClass)) {
            try {
                if(event.isAsync()) {
                    this.eventScheduler.submit(() -> {
                        event.consume(args);
                    });

                } else {
                    event.consume(args);
                }
            } catch (Exception e) {
                log.error("Error during event {} invocation", e);
            }
        }
    }
}
