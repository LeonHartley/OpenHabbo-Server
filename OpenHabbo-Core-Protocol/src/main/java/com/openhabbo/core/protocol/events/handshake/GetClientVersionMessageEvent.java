package com.openhabbo.core.protocol.events.handshake;

import com.openhabbo.api.networking.communication.protocol.MessageEventConsumer;
import com.openhabbo.core.protocol.events.AbstractMessageEvent;
import com.openhabbo.core.protocol.parsers.handshake.GetClientVersionMessageParser;

import java.util.function.Consumer;

public class GetClientVersionMessageEvent extends AbstractMessageEvent<GetClientVersionMessageParser> {
    public GetClientVersionMessageEvent(Class<? extends MessageEventConsumer> consumer) {
        super(consumer, GetClientVersionMessageParser.class);
    }
}
