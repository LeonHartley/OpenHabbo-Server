package com.openhabbo.api.modules.events.players.args;

import com.openhabbo.api.modules.events.sessions.args.SessionEventArgs;
import com.openhabbo.api.networking.sessions.Session;

public class PlayerAuthenticationEventArgs extends SessionEventArgs {
    private final String ssoTicket;

    private boolean isAuthenticationFailed;

    public PlayerAuthenticationEventArgs(Session session, String ssoTicket) {
        super(session);

        this.ssoTicket = ssoTicket;
    }

    public boolean isAuthenticationFailed() {
        return this.isAuthenticationFailed;
    }

    public void setAuthenticationFailed(boolean authenticationFailed) {
        this.isAuthenticationFailed = authenticationFailed;
    }

    public String getSsoTicket() {
        return ssoTicket;
    }
}
