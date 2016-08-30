package com.openhabbo.core.sessions.messaging.handshake;

import com.google.inject.Inject;
import com.openhabbo.api.networking.communication.protocol.consumers.SessionMessageConsumer;
import com.openhabbo.api.networking.communication.protocol.encryption.RSAClient;
import com.openhabbo.core.protocol.composers.handshake.InitCryptoMessageComposer;
import com.openhabbo.core.protocol.parsers.handshake.InitCryptoMessageParser;

public class InitCryptoMessageConsumer extends SessionMessageConsumer<InitCryptoMessageParser> {

    @Inject
    private RSAClient rsaClient;

    @Override
    public void consume(InitCryptoMessageParser parser) {
        final String p = this.session.getKeyStore().getPrime().toString();
        final String g = this.session.getKeyStore().getGenerator().toString();

        final String prime = this.rsaClient.sign(p);
        final String generator = this.rsaClient.sign(g);

        this.session.getChannel().writeAndFlush(new InitCryptoMessageComposer(prime, generator));
    }
}
