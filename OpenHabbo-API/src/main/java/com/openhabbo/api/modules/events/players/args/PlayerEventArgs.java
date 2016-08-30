package com.openhabbo.api.modules.events.players.args;

import com.openhabbo.api.modules.events.sessions.args.SessionEventArgs;
import com.openhabbo.api.networking.sessions.Session;

public class PlayerEventArgs extends SessionEventArgs {
    public PlayerEventArgs(Session session) {
        super(session);
    }
}
