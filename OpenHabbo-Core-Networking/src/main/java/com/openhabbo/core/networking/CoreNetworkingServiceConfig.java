package com.openhabbo.core.networking;

import com.netflix.governator.annotations.Configuration;

public class CoreNetworkingServiceConfig {
    @Configuration("openhabbo.networking.host")
    public String networkingHost = "0.0.0.0";

    @Configuration("openhabbo.networking.port")
    public int networkingPort = 30000;

    @Configuration("openhabbo.networking.useEpoll")
    public boolean useEpoll = false;

    @Configuration("openhabbo.networking.acceptGroupThreads")
    public int acceptGroupThreads = 8;

    @Configuration("openhabbo.networking.ioGroupThreads")
    public int ioGroupThreads = 8;

    @Configuration("openhabbo.networking.channelGroupThreads")
    public int channelGroupThreads = 8;

    @Configuration("openhabbo.networking.backlog")
    public int backlog = 100;
}
