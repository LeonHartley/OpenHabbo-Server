package com.openhabbo.api.networking.communication.protocol.payload;

public interface IncomingMessagePayload {
    Short getMessageId();

    String readString();

    Boolean readBoolean();

    Integer readInteger();

    Short readShort();

    void reset();
}
