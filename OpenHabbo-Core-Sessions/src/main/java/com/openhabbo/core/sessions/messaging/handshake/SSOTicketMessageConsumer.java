package com.openhabbo.core.sessions.messaging.handshake;

import com.google.inject.Inject;
import com.openhabbo.api.game.players.auth.AuthenticationService;
import com.openhabbo.api.networking.communication.protocol.consumers.SessionMessageConsumer;
import com.openhabbo.core.protocol.events.handshake.SSOTicketMessageEvent;
import com.openhabbo.core.protocol.parsers.handshake.SSOTicketMessageParser;

public class SSOTicketMessageConsumer extends SessionMessageConsumer<SSOTicketMessageParser> {

    @Inject
    private AuthenticationService authenticationService;

    @Override
    public void consume(SSOTicketMessageParser parser) {
        this.authenticationService.authenticatePlayer(this.session, parser.getTicket());

        this.session.getMessageHandler().unregisterEvent(SSOTicketMessageEvent.class);
    }
}
