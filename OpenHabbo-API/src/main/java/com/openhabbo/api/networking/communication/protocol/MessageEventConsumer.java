package com.openhabbo.api.networking.communication.protocol;

import com.openhabbo.api.networking.sessions.Session;

public interface MessageEventConsumer<T extends MessageParser> {
    void consume(T parser);
}
