package com.openhabbo.core.sessions;

import com.google.inject.AbstractModule;
import com.openhabbo.api.networking.communication.ChannelProxy;

import java.util.UUID;

public class SessionModule extends AbstractModule {
    private final UUID sessionId;
    private final ChannelProxy channelProxy;

    public SessionModule(UUID sessionId, ChannelProxy channelProxy) {
        this.sessionId = sessionId;
        this.channelProxy = channelProxy;
    }

    @Override
    protected void configure() {
        bind(ChannelProxy.class).toInstance(this.channelProxy);
        bind(UUID.class).toInstance(this.sessionId);
    }
}
