package com.openhabbo.api.game.players.auth;

import com.openhabbo.api.networking.sessions.Session;

public interface AuthenticationService {
    void authenticatePlayer(Session session, String ssoTicket);
}
