package com.openhabbo.core.protocol.parsers.handshake;

import com.openhabbo.api.networking.communication.protocol.payload.IncomingMessagePayload;
import com.openhabbo.core.protocol.parsers.AbstractMessageParser;

public class SSOTicketMessageParser extends AbstractMessageParser {
    private String ssoTicket;

    @Override
    public boolean parse(IncomingMessagePayload data) {
        this.ssoTicket = data.readString();

        return true;
    }

    public String getTicket() {
        return ssoTicket;
    }
}
