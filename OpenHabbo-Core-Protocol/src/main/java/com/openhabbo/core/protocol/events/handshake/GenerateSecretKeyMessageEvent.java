package com.openhabbo.core.protocol.events.handshake;

import com.openhabbo.api.networking.communication.protocol.MessageEventConsumer;
import com.openhabbo.core.protocol.events.AbstractMessageEvent;
import com.openhabbo.core.protocol.parsers.handshake.GenerateSecretKeyMessageParser;

public class GenerateSecretKeyMessageEvent extends AbstractMessageEvent<GenerateSecretKeyMessageParser> {
    public GenerateSecretKeyMessageEvent(Class<? extends MessageEventConsumer> consumerClass) {
        super(consumerClass, GenerateSecretKeyMessageParser.class);
    }
}
