package com.openhabbo.core.networking.channels;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.core.networking.protocol.codec.MessageDecoder;
import com.openhabbo.core.networking.protocol.codec.MessageEncoder;
import com.openhabbo.core.networking.protocol.codec.XMLPolicyDecoder;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

@ChannelHandler.Sharable
@Singleton
public class CoreChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final StringEncoder stringEncoder;
    private final CoreChannelHandler channelHandler;

    private EventExecutorGroup executor;

    @Inject
    public CoreChannelInitializer(CoreChannelHandler channelHandler) {
        this.channelHandler = channelHandler;

        this.stringEncoder = new StringEncoder(CharsetUtil.UTF_8);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        if (this.executor == null) {
            throw new Exception("ChannelInitializer not configured correctly.");
        }

        ch.config().setTrafficClass(0x18);

        ch.pipeline()
                .addLast("xmlDecoder", new XMLPolicyDecoder())
                .addLast("stringEncoder", this.stringEncoder)
                .addLast("messageDecoder", new MessageDecoder())
                .addLast("messageEncoder", new MessageEncoder());

        ch.pipeline().addLast(this.executor, "clientHandler", this.channelHandler);

    }

    public void setExecutor(EventExecutorGroup executor) {
        this.executor = executor;
    }
}
