package com.openhabbo.webapp;

import com.google.inject.AbstractModule;
import com.openhabbo.api.webapp.config.ConfigDao;
import com.openhabbo.api.webapp.config.ConfigService;
import com.openhabbo.api.webapp.users.UserDao;
import com.openhabbo.webapp.config.CoreConfigService;

public class WebAppModule extends AbstractModule {
    @Override
    protected void configure() {
        // Storage stuff
//        bind(ConfigDao.class).to(CoreConfigDao.class);
//
//        bind(ConfigService.class).to(CoreConfigService.class);
    }
}
