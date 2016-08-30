package com.openhabbo.api.modules.events.sessions.args;

import com.openhabbo.api.modules.events.EventArgs;
import com.openhabbo.api.networking.sessions.Session;

public class SessionEventArgs extends EventArgs {
    private final Session session;

    public SessionEventArgs(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return this.session;
    }
}
