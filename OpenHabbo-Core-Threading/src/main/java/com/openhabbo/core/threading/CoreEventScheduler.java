package com.openhabbo.core.threading;

import com.google.common.collect.Maps;
import com.google.inject.Singleton;
import com.netflix.governator.annotations.Configuration;
import com.openhabbo.api.threading.EventScheduler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class CoreEventScheduler implements EventScheduler {
    private static AtomicInteger eventSchedulerCount = new AtomicInteger(0);

    private final Logger log = LogManager.getLogger(CoreEventScheduler.class);

    @Configuration("openhabbo.threading.coreThreadCount")
    private int coreThreadCount = 8;

    private ScheduledExecutorService executorService;
    private final Map<UUID, Future> futures = Maps.newConcurrentMap();

    @Override
    public void initialise() {
        if (this.executorService != null) {
            log.warn("EventScheduler is already initialised!");
            return;
        }

        final int eventSchedulerCount = CoreEventScheduler.eventSchedulerCount.incrementAndGet();

        this.executorService = Executors.newScheduledThreadPool(this.coreThreadCount, new ThreadFactory() {
            private int counter = 0;

            @Override
            public Thread newThread(Runnable r) {
                final Thread thread = new Thread(r);

                thread.setName("OpenHabbo-Event-Scheduler-" + eventSchedulerCount + "-" + counter);
                counter++;
                return thread;
            }
        });
    }

    @Override
    public UUID scheduleEvent(Runnable runnable, long initialDelay, long period, TimeUnit unit) {
        if(this.executorService == null) {
            this.initialise();
        }

        final UUID scheduledEventId = UUID.randomUUID();
        final Future future = this.executorService.scheduleAtFixedRate(runnable, initialDelay, period, unit);

        this.futures.put(scheduledEventId, future);

        return scheduledEventId;
    }

    @Override
    public void executeEvent(Runnable runnable, long delay, TimeUnit unit) {
        if(this.executorService == null) {
            this.initialise();
        }

        this.executorService.schedule(runnable, delay, unit);
    }

    @Override
    public boolean stopScheduledEvent(UUID event) {
        if(!this.futures.containsKey(event)) {
            return false;
        }

        final Future future = this.futures.get(event);
        future.cancel(false);

        this.futures.remove(event);
        return true;
    }

    @Override
    public void submit(Runnable runnable) {
        if(this.executorService == null) {
            this.initialise();
        }

        this.executorService.submit(runnable);
    }
}
