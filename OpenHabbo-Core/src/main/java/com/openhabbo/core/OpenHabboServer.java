package com.openhabbo.core;

import com.google.inject.Injector;
import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.governator.configuration.ArchaiusConfigurationProvider;
import com.netflix.governator.guice.BootstrapBinder;
import com.netflix.governator.guice.BootstrapModule;
import com.netflix.governator.guice.LifecycleInjector;
import com.openhabbo.api.services.Service;
import com.openhabbo.core.modules.injection.OpenHabboModule;
import com.openhabbo.core.services.ServerService;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class OpenHabboServer {
    private static final Logger log = LogManager.getLogger(OpenHabboServer.class);

    public static String[] RUNTIME_ARGS;

    private static final Map<String, Class<? extends Service>> services = new HashMap<String, Class<? extends Service>>() {{
        put("--server", ServerService.class);
    }};

    public static void main(String[] args) {
        OpenHabboServer.RUNTIME_ARGS = args;

        initialiseConfiguration();

        if (OpenHabboServer.RUNTIME_ARGS.length != 0) {
            final String serviceType = OpenHabboServer.RUNTIME_ARGS[0];

            if(!services.containsKey(serviceType)) {
                return;
            }

            Injector injector = initialiseInjector();

            final Class<? extends Service> serviceClass = services.get(serviceType);
            final Service service = injector.getInstance(serviceClass);

            service.onServiceStarted();
        } else {
            System.out.println("Expected first argument to define which type of service this instance serves.");
            System.out.println("    Available services:");

            for(Map.Entry<String, Class<? extends Service>> services : services.entrySet()) {
                System.out.printf("         %s | %s\r\n", services.getKey(), services.getValue());
            }
        }
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
