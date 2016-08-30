package com.openhabbo.webapp;

import com.google.inject.Inject;
import com.netflix.governator.annotations.Configuration;
import com.openhabbo.webapp.controllers.home.IndexController;
import org.apache.velocity.app.VelocityEngine;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static spark.Spark.get;

public class WebAppBootstrap {
    @Configuration(value = "openhabbo.webapp.webserverPort")
    private int webserverPort = 3000;

    @Inject
    private IndexController indexController;

    public void initialise() {
        Spark.port(this.webserverPort);
        Spark.externalStaticFileLocation("static/");

        final VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine(new VelocityEngine("velocity.properties"));

        get("/", this.indexController::index, velocityTemplateEngine);
    }
}
