package com.openhabbo.api.networking.communication.protocol.payload;

public interface OutgoingMessagePayload {
    void writeString(String string);

    void writeInteger(Integer integer);

    void writeDouble(Double dbl);

    void writeShort(Short shrt);
}
