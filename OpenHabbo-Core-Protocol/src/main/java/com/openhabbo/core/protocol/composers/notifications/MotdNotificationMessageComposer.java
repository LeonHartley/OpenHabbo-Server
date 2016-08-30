package com.openhabbo.core.protocol.composers.notifications;

import com.openhabbo.api.networking.communication.protocol.payload.OutgoingMessagePayload;
import com.openhabbo.core.protocol.composers.AbstractMessageComposer;

public class MotdNotificationMessageComposer extends AbstractMessageComposer {
    private final String text;

    public MotdNotificationMessageComposer(String text) {
        this.text = text;
    }

    @Override
    public void onCompose(OutgoingMessagePayload message) {
        message.writeInteger(1);
        message.writeString(this.text);
    }
}
