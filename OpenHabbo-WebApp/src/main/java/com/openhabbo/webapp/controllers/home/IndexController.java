package com.openhabbo.webapp.controllers.home;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.webapp.controllers.Controller;
import com.openhabbo.webapp.globals.GlobalModelService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class IndexController extends Controller {

    @Inject
    public IndexController(GlobalModelService globalModelService) {
        super(globalModelService);
    }

    public ModelAndView index(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        return this.buildResponse(model, "templates/index.vm");
    }
}
