package com.openhabbo.api.networking.communication;

import com.openhabbo.api.networking.communication.protocol.MessageComposer;

public interface ChannelProxy {
    void write(MessageComposer composer);

    void writeAndFlush(MessageComposer composer);

    void flush();

    void close();

    void initialiseEncryption(byte[] key);
}