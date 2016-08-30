package com.openhabbo.core.networking.protocol;

import com.openhabbo.api.networking.communication.protocol.payload.OutgoingMessagePayload;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

public class OutgoingMessageData implements OutgoingMessagePayload {

    private final ByteBuf buffer;

    public OutgoingMessageData(ByteBuf buffer) {
        this.buffer = buffer;
    }

    @Override
    public void writeString(String string) {
        byte[] data = string.getBytes(Charset.forName("UTF-8"));

        this.buffer.writeShort(data.length);
        this.buffer.writeBytes(string.getBytes());
    }

    @Override
    public void writeInteger(Integer integer) {
        this.buffer.writeInt(integer);
    }

    @Override
    public void writeDouble(Double dbl) {
        this.buffer.writeDouble(dbl);
    }

    @Override
    public void writeShort(Short shrt) {
        this.buffer.writeShort(shrt);
    }

    public boolean hasLength() {
        return this.buffer.getInt(0) > -1;
    }
}
