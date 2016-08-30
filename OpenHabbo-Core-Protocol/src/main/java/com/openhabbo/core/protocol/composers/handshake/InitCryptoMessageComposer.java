package com.openhabbo.core.protocol.composers.handshake;

import com.openhabbo.api.networking.communication.protocol.payload.OutgoingMessagePayload;
import com.openhabbo.core.protocol.composers.AbstractMessageComposer;

public class InitCryptoMessageComposer  extends AbstractMessageComposer {

    private final String prime;
    private final String generator;

    public InitCryptoMessageComposer(final String prime, final String generator) {
        this.prime = prime;
        this.generator = generator;
    }

    @Override
    public void onCompose(OutgoingMessagePayload message) {
        message.writeString(this.prime);
        message.writeString(this.generator);
    }
}
