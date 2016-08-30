package com.openhabbo.core.networking.protocol.codec;

import com.google.inject.Singleton;
import com.openhabbo.api.networking.communication.protocol.MessageComposer;
import com.openhabbo.core.networking.protocol.OutgoingMessageData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@ChannelHandler.Sharable
public class MessageEncoder extends MessageToByteEncoder<MessageComposer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageComposer messageComposer, ByteBuf byteBuf) throws Exception {
        final OutgoingMessageData messageData = new OutgoingMessageData(byteBuf);

        messageData.writeInteger(-1);
        messageData.writeShort(messageComposer.getMessageId());

        messageComposer.onCompose(messageData);

        if (!messageData.hasLength()) {
            byteBuf.setInt(0, byteBuf.writerIndex() - 4);
        }
    }
}
