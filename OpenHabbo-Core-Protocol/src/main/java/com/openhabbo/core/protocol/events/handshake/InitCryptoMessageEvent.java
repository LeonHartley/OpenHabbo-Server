package com.openhabbo.core.protocol.events.handshake;

import com.openhabbo.api.networking.communication.protocol.MessageEventConsumer;
import com.openhabbo.core.protocol.events.AbstractMessageEvent;
import com.openhabbo.core.protocol.parsers.handshake.InitCryptoMessageParser;

public class InitCryptoMessageEvent extends AbstractMessageEvent<InitCryptoMessageParser> {
    public InitCryptoMessageEvent(Class<? extends MessageEventConsumer> consumerClass) {
        super(consumerClass, InitCryptoMessageParser.class);
    }
}
