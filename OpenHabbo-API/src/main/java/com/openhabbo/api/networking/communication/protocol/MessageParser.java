package com.openhabbo.api.networking.communication.protocol;

import com.openhabbo.api.networking.communication.protocol.payload.IncomingMessagePayload;

public interface MessageParser {
    boolean parse(IncomingMessagePayload data);

    void flush();
}
