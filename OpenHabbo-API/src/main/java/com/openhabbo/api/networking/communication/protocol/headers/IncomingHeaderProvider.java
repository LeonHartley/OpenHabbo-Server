package com.openhabbo.api.networking.communication.protocol.headers;

import com.openhabbo.api.networking.communication.protocol.MessageEvent;

public interface IncomingHeaderProvider {
    Class<? extends MessageEvent> getEventClass(short eventId);
}
