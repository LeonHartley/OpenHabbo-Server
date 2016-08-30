package com.openhabbo.webapp.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.api.webapp.config.ConfigDao;
import com.openhabbo.api.webapp.config.ConfigService;
import com.openhabbo.api.webapp.config.WebAppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class CoreConfigService implements ConfigService {
    private final Logger log = LogManager.getLogger(CoreConfigService.class);
    private final ConfigDao configDao;

    private WebAppConfig configInstance;

    @Inject
    public CoreConfigService(ConfigDao configDao) {
        this.configDao = configDao;
    }

    public WebAppConfig getConfig() {
        if (this.configInstance == null) {
            this.configInstance = this.configDao.getConfig();
        }

        return this.configInstance;
    }

    @Override
    public void reloadConfig() {
        this.configInstance = this.configDao.getConfig();
    }

    @Override
    public void saveConfig() {
        log.warn("Saving configuration is not implemented yet!");
    }
}
