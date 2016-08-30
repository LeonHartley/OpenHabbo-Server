package com.openhabbo.webapp.config;

import com.openhabbo.api.webapp.config.WebAppConfig;

public class ConfigBean implements WebAppConfig {
    private String siteName;
    private String assetsUrl;

    public ConfigBean(String siteName, String assetsUrl) {
        this.siteName = siteName;
        this.assetsUrl = assetsUrl;
    }

    @Override
    public String getSiteName() {
        return this.siteName;
    }

    @Override
    public String getAssetsUrl() {
        return this.assetsUrl;
    }
}
