package com.openhabbo.api.threading;

import com.openhabbo.api.util.Initialisable;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public interface EventScheduler extends Initialisable {
    UUID scheduleEvent(Runnable runnable, long initialDelay, long period, TimeUnit unit);

    void executeEvent(Runnable runnable, long delay, TimeUnit unit);

    boolean stopScheduledEvent(UUID event);

    void submit(Runnable runnable);
}
