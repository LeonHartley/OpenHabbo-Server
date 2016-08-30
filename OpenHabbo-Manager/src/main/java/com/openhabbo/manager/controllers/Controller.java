package com.openhabbo.manager.controllers;

import com.openhabbo.manager.models.GlobalModelService;
import spark.ModelAndView;

import java.util.Map;

public abstract class Controller {
    private final GlobalModelService modelService;

    public Controller(GlobalModelService modelService) {
        this.modelService = modelService;
    }

    public ModelAndView buildResponse(Map<String, Object> model, String templateName) {
        return new ModelAndView(this.getModelService().fillGlobals(model), templateName);
    }

    public GlobalModelService getModelService() {
        return this.modelService;
    }
}
