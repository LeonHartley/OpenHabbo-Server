package com.openhabbo.api.modules.events;

public interface EventRegistry<T> {
    void registerEvent(T event);

    void unregisterEvent(Class<? extends T> eventClass);

    <A extends EventArgs> void invoke(Class<? extends T> eventClass, A args);
}
