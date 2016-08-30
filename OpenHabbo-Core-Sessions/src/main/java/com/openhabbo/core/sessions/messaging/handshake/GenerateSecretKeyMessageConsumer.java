package com.openhabbo.core.sessions.messaging.handshake;

import com.google.inject.Inject;
import com.openhabbo.api.networking.communication.protocol.consumers.SessionMessageConsumer;
import com.openhabbo.api.networking.communication.protocol.encryption.RSAClient;
import com.openhabbo.core.protocol.composers.handshake.SecretKeyMessageComposer;
import com.openhabbo.core.protocol.events.handshake.GenerateSecretKeyMessageEvent;
import com.openhabbo.core.protocol.events.handshake.InitCryptoMessageEvent;
import com.openhabbo.core.protocol.events.handshake.SSOTicketMessageEvent;
import com.openhabbo.core.protocol.parsers.handshake.GenerateSecretKeyMessageParser;

public class GenerateSecretKeyMessageConsumer extends SessionMessageConsumer<GenerateSecretKeyMessageParser> {

    @Inject
    private RSAClient rsaClient;

    @Override
    public void consume(GenerateSecretKeyMessageParser parser) {
        final String key = this.rsaClient.decrypt(parser.getPublicKey()).replace(Character.toString((char) 0), "");

        final String p = this.session.getKeyStore().getPublicKey().toString();
        final String signedP = this.rsaClient.sign(p);

        this.session.getChannel().writeAndFlush(new SecretKeyMessageComposer(signedP));

        this.session.getKeyStore().generateSharedKey(key);
        final byte[] sharedKey = this.session.getKeyStore().getSharedKey().toByteArray();

        this.session.getChannel().initialiseEncryption(sharedKey);

        this.session.getMessageHandler().unregisterEvent(InitCryptoMessageEvent.class);
        this.session.getMessageHandler().unregisterEvent(GenerateSecretKeyMessageEvent.class);

        this.session.getMessageHandler().registerEvent(new SSOTicketMessageEvent(SSOTicketMessageConsumer.class));
    }
}
