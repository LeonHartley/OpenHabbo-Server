package com.openhabbo.core.protocol.events.handshake;

import com.openhabbo.api.networking.communication.protocol.MessageEventConsumer;
import com.openhabbo.core.protocol.events.AbstractMessageEvent;
import com.openhabbo.core.protocol.parsers.handshake.SSOTicketMessageParser;

import java.util.function.Consumer;

public class SSOTicketMessageEvent extends AbstractMessageEvent<SSOTicketMessageParser> {
    public SSOTicketMessageEvent(Class<? extends MessageEventConsumer> consumerClass) {
        super(consumerClass, SSOTicketMessageParser.class);
    }
}
