package com.openhabbo.api.networking.communication.protocol;

import com.openhabbo.api.networking.communication.protocol.payload.OutgoingMessagePayload;

public interface MessageComposer {
    void onCompose(OutgoingMessagePayload message);

    short getMessageId();
}
