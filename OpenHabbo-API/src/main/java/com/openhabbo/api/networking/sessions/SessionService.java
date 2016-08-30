package com.openhabbo.api.networking.sessions;

import com.openhabbo.api.networking.communication.ChannelProxy;

import java.util.UUID;

public interface SessionService {
    Session getSessionById(UUID id);

    Session createSession(UUID id, ChannelProxy channel);

    void disposeSession(Session session);
}
