package com.openhabbo.core.protocol.parsers;

import com.openhabbo.api.networking.communication.protocol.MessageParser;
import com.openhabbo.api.networking.communication.protocol.payload.IncomingMessagePayload;

public abstract class AbstractMessageParser implements MessageParser {
    @Override
    public void flush() {
        // by default, we have nothing to flush, if any parsers have lists or other objects tied to them,
        // this should be overridden.
    }

    @Override
    public boolean parse(IncomingMessagePayload payload) {
        return true;
    }
}
