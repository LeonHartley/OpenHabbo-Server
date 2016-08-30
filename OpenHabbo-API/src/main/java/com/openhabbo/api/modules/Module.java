package com.openhabbo.api.modules;

import com.google.inject.Inject;
import com.openhabbo.api.modules.events.SystemEventRegistry;

import java.util.UUID;

public abstract class Module {

    @Inject
    private SystemEventRegistry eventRegistry;

    private final UUID moduleId;

    public Module(UUID moduleId) {
        this.moduleId = moduleId;
    }

    public UUID getModuleId() {
        return this.moduleId;
    }

    public abstract void onModuleLoad();

    public abstract void onModuleDispose();

    protected SystemEventRegistry getEventRegistry() {
        return this.eventRegistry;
    }
}
