package com.openhabbo.core.networking.protocol.codec;

import com.openhabbo.core.networking.protocol.codec.encryption.RC4;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class EncryptionDecoder extends ByteToMessageDecoder {

    private final RC4 rc4;

    public EncryptionDecoder(byte[] key) {
        this.rc4 = new RC4(key);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        out.add(this.rc4.decipher(in));
    }
}
