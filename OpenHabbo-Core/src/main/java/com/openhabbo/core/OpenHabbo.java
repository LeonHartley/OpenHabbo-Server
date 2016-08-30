package com.openhabbo.core;

import com.google.inject.Inject;
import com.openhabbo.api.services.Service;
import com.openhabbo.core.modules.ModuleLoader;
import com.openhabbo.core.services.ServerService;

import java.util.HashMap;
import java.util.Map;

public class OpenHabbo {

    private final ModuleLoader moduleLoader;

    private final Map<String, Class<? extends Service>> services = new HashMap<String, Class<? extends Service>>() {{
        put("--server", ServerService.class);
    }};

    @Inject
    public OpenHabbo(ModuleLoader moduleLoader) {
        this.moduleLoader = moduleLoader;
    }

    public void initialise() {
        // Here we check which service we want to start.

        if (OpenHabboServer.RUNTIME_ARGS.length != 0) {
            final String serviceType = OpenHabboServer.RUNTIME_ARGS[0];

            switch (serviceType) {

            }
        }
    }
}
