package com.openhabbo.manager.models;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Map;

@Singleton
public class GlobalModelService {

    @Inject
    public GlobalModelService() {

    }

    public Map<String, Object> fillGlobals(Map<String, Object> model) {

        return model;
    }
}