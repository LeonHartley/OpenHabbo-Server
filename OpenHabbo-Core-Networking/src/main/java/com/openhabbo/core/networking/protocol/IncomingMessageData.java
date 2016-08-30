package com.openhabbo.core.networking.protocol;

import com.openhabbo.api.networking.communication.protocol.payload.IncomingMessagePayload;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class IncomingMessageData implements IncomingMessagePayload {
    private short id;
    private final ByteBuf buffer;

    public IncomingMessageData(ByteBuf buf) {
        this.buffer = (buf != null) && (buf.readableBytes() > 0) ? buf : Unpooled.EMPTY_BUFFER;

        if (this.content().readableBytes() >= 2) {
            this.id = this.readShort();
        } else {
            this.id = 0;
        }
    }

    @Override
    public Short getMessageId() {
        return this.id;
    }

    @Override
    public Short readShort() {
        return this.content().readShort();
    }

    @Override
    public void reset() {
        this.buffer.resetReaderIndex();
        this.id = this.readShort();
    }

    @Override
    public Integer readInteger() {
        try {
            return this.content().readInt();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Boolean readBoolean() {
        return this.content().readByte() == 1;
    }

    @Override
    public String readString() {
        int length = this.readShort();
        byte[] data = this.content().readBytes((length)).array();

        return new String(data);
    }

    @Override
    public String toString() {
        String body = this.content().toString((Charset.defaultCharset()));

        for (int i = 0; i < 13; i++) {
            body = body.replace(Character.toString((char) i), "[" + i + "]");
        }

        return body;
    }

    private ByteBuf content() {
        return this.buffer;
    }
}
