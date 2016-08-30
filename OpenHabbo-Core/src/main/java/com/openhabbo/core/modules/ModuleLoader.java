package com.openhabbo.core.modules;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.openhabbo.api.modules.Module;
import com.openhabbo.api.modules.events.SystemEventRegistry;
import com.openhabbo.module.motd.MotdModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.UUID;

@Singleton
public class ModuleLoader {
    private static final Logger log = LogManager.getLogger(ModuleLoader.class);

    private final SystemEventRegistry eventRegistry;
    private final Injector injector;

    private final Map<UUID, Module> modules;

    @Inject
    public ModuleLoader(SystemEventRegistry eventRegistry, Injector injector) {
        this.eventRegistry = eventRegistry;
        this.injector = injector;

        this.modules = Maps.newConcurrentMap();
    }

    public void loadModules() {
        // Load static modules first, then we'll dynamically load JAR files from a directory or something.
        this.loadModule(MotdModule.class);
    }

    private void loadModule(Class<? extends Module> module) {
        try {
            final Module moduleInstance = this.injector.getInstance(module);

            this.modules.put(moduleInstance.getModuleId(), moduleInstance);

            log.info("Loaded module {}", module.getName());
            moduleInstance.onModuleLoad();
        } catch (Exception e) {
            log.info("Failed to load module {}", e);
        }
    }

    public Injector getInjector() {
        return this.injector;
    }
}
