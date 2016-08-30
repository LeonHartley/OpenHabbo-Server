package com.openhabbo.core.protocol.composers.handshake;

import com.openhabbo.api.networking.communication.protocol.payload.OutgoingMessagePayload;
import com.openhabbo.core.protocol.composers.AbstractMessageComposer;

public class SecretKeyMessageComposer extends AbstractMessageComposer {

    private final String publicKey;

    public SecretKeyMessageComposer(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public void onCompose(OutgoingMessagePayload message) {
        message.writeString(publicKey);
    }
}
