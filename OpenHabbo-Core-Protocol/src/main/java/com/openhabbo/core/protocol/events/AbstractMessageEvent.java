package com.openhabbo.core.protocol.events;

import com.openhabbo.api.networking.communication.protocol.MessageEvent;
import com.openhabbo.api.networking.communication.protocol.MessageEventConsumer;
import com.openhabbo.core.protocol.parsers.AbstractMessageParser;

public abstract class AbstractMessageEvent<T extends AbstractMessageParser> implements MessageEvent {
    private final Class<? extends MessageEventConsumer> consumer;
    private final Class<T> parser;

    public AbstractMessageEvent(Class<? extends MessageEventConsumer> consumerClass, Class<T> parser) {
        this.consumer = consumerClass;
        this.parser = parser;
    }

    @Override
    public Class<T> getParserClass() {
        return this.parser;
    }

    @Override
    public Class<? extends MessageEventConsumer> getConsumer() {
        return this.consumer;
    }
}
