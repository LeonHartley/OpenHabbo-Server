package com.openhabbo.core.networking.channels;


import com.google.inject.Inject;
import com.openhabbo.api.networking.communication.ChannelProxy;
import com.openhabbo.api.networking.communication.protocol.MessageComposer;
import com.openhabbo.core.networking.protocol.codec.EncryptionDecoder;
import io.netty.channel.ChannelHandlerContext;

public class CoreChannelProxy implements ChannelProxy {

    private final ChannelHandlerContext channelHandlerContext;

    public CoreChannelProxy(ChannelHandlerContext channelHandlerContext) {
        this.channelHandlerContext = channelHandlerContext;
    }

    @Override
    public void write(MessageComposer composer) {
        this.channelHandlerContext.write(composer);
    }

    @Override
    public void writeAndFlush(MessageComposer composer) {
        this.channelHandlerContext.writeAndFlush(composer);
    }

    @Override
    public void flush() {
        this.channelHandlerContext.flush();
    }

    @Override
    public void close() {
        this.channelHandlerContext.close();
    }

    @Override
    public void initialiseEncryption(byte[] key) {
        this.channelHandlerContext.pipeline().addBefore("messageDecoder", "encryptionDecoder", new EncryptionDecoder(key));
    }
}
