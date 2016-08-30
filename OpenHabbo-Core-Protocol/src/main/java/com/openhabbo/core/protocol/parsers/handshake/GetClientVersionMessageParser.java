package com.openhabbo.core.protocol.parsers.handshake;

import com.openhabbo.api.networking.communication.protocol.payload.IncomingMessagePayload;
import com.openhabbo.core.protocol.parsers.AbstractMessageParser;

public class GetClientVersionMessageParser extends AbstractMessageParser {
    private String clientVersion;

    @Override
    public boolean parse(IncomingMessagePayload data) {
        this.clientVersion = data.readString();

        return true;
    }

    public String getClientVersion() {
        return clientVersion;
    }
}
