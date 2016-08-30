package com.openhabbo.core;

import com.google.inject.Injector;
import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.governator.configuration.ArchaiusConfigurationProvider;
import com.netflix.governator.guice.BootstrapBinder;
import com.netflix.governator.guice.BootstrapModule;
import com.netflix.governator.guice.LifecycleInjector;
import com.openhabbo.core.modules.injection.OpenHabboModule;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenHabboServer {
    private static final Logger log = LogManager.getLogger(OpenHabboServer.class);

    public static String[] RUNTIME_ARGS;

    public static void main(String[] args) {
        OpenHabboServer.RUNTIME_ARGS = args;

        initialiseConfiguration();

        Injector injector = initialiseInjector();

        OpenHabbo openHabbo = injector.getInstance(OpenHabbo.class);

        openHabbo.initialise();
    }

    private static Injector initialiseInjector() {
        return LifecycleInjector.builder().usingBasePackages("com.openhabbo").withBootstrapModule(
                new BootstrapModule() {
                    @Override
                    public void configure(BootstrapBinder binder) {
                        binder.bindConfigurationProvider().toInstance(ArchaiusConfigurationProvider.builder().build());
                    }
                }
        ).withModuleClass(OpenHabboModule.class).build().createInjector();
    }

    private static void initialiseConfiguration() {
        try {
            ConcurrentMapConfiguration propertiesConfig = new ConcurrentMapConfiguration(new PropertiesConfiguration("openhabbo.properties"));

            ConcurrentCompositeConfiguration configuration = new ConcurrentCompositeConfiguration();
            configuration.addConfiguration(propertiesConfig);

            ConfigurationManager.install(configuration);
        } catch (Exception e) {
            log.error("Failed to initialise configuration");
        }
    }
}
