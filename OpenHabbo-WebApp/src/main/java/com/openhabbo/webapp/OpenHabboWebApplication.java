package com.openhabbo.webapp;

import com.google.inject.Injector;
import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.governator.configuration.ArchaiusConfigurationProvider;
import com.netflix.governator.guice.BootstrapBinder;
import com.netflix.governator.guice.BootstrapModule;
import com.netflix.governator.guice.LifecycleInjector;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenHabboWebApplication {
    private static final Logger log = LogManager.getLogger(OpenHabboWebApplication.class);

    public static void main(String[] args) {
        initialiseConfiguration();

        Injector injector = initialiseInjector();

        WebAppBootstrap webApp = injector.getInstance(WebAppBootstrap.class);

        webApp.initialise();
    }

    private static Injector initialiseInjector() {
        return LifecycleInjector.builder().usingBasePackages("com.openhabbo.webapp").withBootstrapModule(
                new BootstrapModule() {
                    @Override
                    public void configure(BootstrapBinder binder) {
                        binder.bindConfigurationProvider().toInstance(ArchaiusConfigurationProvider.builder().build());
                    }
                }
        ).withModuleClass(WebAppModule.class).build().createInjector();
    }

    private static void initialiseConfiguration() {
        try {
            ConcurrentMapConfiguration propertiesConfig = new ConcurrentMapConfiguration(new PropertiesConfiguration("openhabbo-webapp.properties"));

            ConcurrentCompositeConfiguration configuration = new ConcurrentCompositeConfiguration();
            configuration.addConfiguration(propertiesConfig);

            ConfigurationManager.install(configuration);
        } catch (Exception e) {
            log.error("Failed to initialise configuration");
        }
    }
}