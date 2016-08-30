package com.openhabbo.api.webapp.config;

public interface ConfigService {
    WebAppConfig getConfig();

    void reloadConfig();

    void saveConfig();
}
