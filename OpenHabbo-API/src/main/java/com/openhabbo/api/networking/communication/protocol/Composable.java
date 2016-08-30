package com.openhabbo.api.networking.communication.protocol;

import com.openhabbo.api.networking.communication.protocol.payload.OutgoingMessagePayload;

public interface Composable {
    void compose(OutgoingMessagePayload message);
}
