package com.openhabbo.api.networking.communication.protocol;

public interface MessageEvent {
    public Class<? extends MessageParser> getParserClass();

    public Class<? extends MessageEventConsumer> getConsumer();
}
