package com.openhabbo.api.modules.events.sessions;

import com.openhabbo.api.modules.events.Event;
import com.openhabbo.api.modules.events.sessions.args.SessionEventArgs;

import java.util.function.Consumer;

public class SessionActiveEvent extends Event<SessionEventArgs> {
    public SessionActiveEvent(Consumer<SessionEventArgs> callback) {
        super(callback);
    }
}
