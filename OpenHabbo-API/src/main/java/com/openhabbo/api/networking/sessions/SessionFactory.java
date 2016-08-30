package com.openhabbo.api.networking.sessions;

import com.openhabbo.api.networking.communication.ChannelProxy;

import java.util.UUID;

public interface SessionFactory {
    Session create(UUID id, ChannelProxy proxy);
}
