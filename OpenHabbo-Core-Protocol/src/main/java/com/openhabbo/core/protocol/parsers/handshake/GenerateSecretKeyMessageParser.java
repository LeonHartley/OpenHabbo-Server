package com.openhabbo.core.protocol.parsers.handshake;

import com.openhabbo.api.networking.communication.protocol.payload.IncomingMessagePayload;
import com.openhabbo.core.protocol.parsers.AbstractMessageParser;

public class GenerateSecretKeyMessageParser extends AbstractMessageParser {

    private String publicKey;

    @Override
    public boolean parse(IncomingMessagePayload payload) {
        this.publicKey = payload.readString();
        return true;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
