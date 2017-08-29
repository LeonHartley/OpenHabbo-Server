package com.openhabbo.core.sessions.messaging.handshake;

import com.openhabbo.api.networking.communication.protocol.consumers.SessionMessageConsumer;
import com.openhabbo.core.protocol.events.handshake.GenerateSecretKeyMessageEvent;
import com.openhabbo.core.protocol.events.handshake.InitCryptoMessageEvent;
import com.openhabbo.core.protocol.parsers.handshake.GetClientVersionMessageParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientVersionMessageConsumer extends SessionMessageConsumer<GetClientVersionMessageParser> {
    private static final Logger log = LogManager.getLogger(ClientVersionMessageConsumer.class);

    private final String SUPPORTED_VERSION = "PRODUCTION-201705151314-310198720";

    @Override
    public void consume(GetClientVersionMessageParser parser) {
        log.debug("Client connected with client version: {}", parser.getClientVersion());

        if(!parser.getClientVersion().equals(SUPPORTED_VERSION)) {
            this.session.disconnect();
            log.warn("Invalid version, expecting {}, got {}", SUPPORTED_VERSION, parser.getClientVersion());
            return;
        }

        this.session.getMessageHandler().registerEvent(new InitCryptoMessageEvent(InitCryptoMessageConsumer.class));
        this.session.getMessageHandler().registerEvent(new GenerateSecretKeyMessageEvent(GenerateSecretKeyMessageConsumer.class));
    }
}
