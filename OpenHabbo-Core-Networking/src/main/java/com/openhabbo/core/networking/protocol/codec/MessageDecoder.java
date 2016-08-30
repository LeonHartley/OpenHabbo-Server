package com.openhabbo.core.networking.protocol.codec;

import com.google.inject.Singleton;
import com.openhabbo.core.networking.protocol.IncomingMessageData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        try {
            if (in.readableBytes() < 6) {
                return;
            }

            in.markReaderIndex();
            int length = in.readInt();

            if (!(in.readableBytes() >= length)) {
                in.resetReaderIndex();
                return;
            }

            if (length < 0) {
                return;
            }

            out.add(new IncomingMessageData(in.readBytes(length)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}