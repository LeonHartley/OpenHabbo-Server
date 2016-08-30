package com.openhabbo.webapp.globals;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.api.webapp.config.ConfigService;

import java.util.Map;

@Singleton
public class GlobalModelService {

    @Inject
    public GlobalModelService() {

    }

    public Map<String, Object> fillGlobals(Map<String, Object> model) {
        model.put("hi", "Hello!");

        return model;
    }
}
