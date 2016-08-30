package com.openhabbo.manager;

import com.google.inject.Inject;
import com.netflix.governator.annotations.Configuration;
import com.openhabbo.manager.controllers.IndexController;
import spark.Spark;
import static spark.Spark.*;

public class ManagerBootstrap {

    @Configuration(value = "openhabbo.webapp.webserverPort")
    private int webserverPort = 3000;

    @Inject
    private IndexController indexController;

    public void initialise() {
        Spark.port(this.webserverPort);

        get("/", this.indexController::index);
    }
}
