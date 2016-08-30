package com.openhabbo.api.networking.communication.protocol.consumers;

import com.google.inject.Inject;
import com.openhabbo.api.networking.communication.protocol.MessageEventConsumer;
import com.openhabbo.api.networking.communication.protocol.MessageParser;
import com.openhabbo.api.networking.sessions.Session;

public abstract class SessionMessageConsumer<T extends MessageParser> implements MessageEventConsumer<T> {
    @Inject
    protected Session session;
}
