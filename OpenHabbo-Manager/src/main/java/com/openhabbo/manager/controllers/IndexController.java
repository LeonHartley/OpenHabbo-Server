package com.openhabbo.manager.controllers;

import com.openhabbo.manager.models.GlobalModelService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndexController extends Controller {
    public IndexController(GlobalModelService modelService) {
        super(modelService);
    }

    public ModelAndView index(Request request, Response response) {

        response.body("hi");
        return null;
    }
}
