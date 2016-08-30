package com.openhabbo.api.networking.sessions;

import com.openhabbo.api.modules.events.EventArgs;
import com.openhabbo.api.modules.events.EventRegistry;
import com.openhabbo.api.networking.communication.protocol.MessageEvent;
import com.openhabbo.api.networking.communication.protocol.payload.IncomingMessagePayload;
import com.openhabbo.api.util.Disposable;
import com.openhabbo.api.util.Initialisable;

public interface SessionMessageHandler extends EventRegistry<MessageEvent>, Disposable {
    void handleEvent(IncomingMessagePayload payload);

    Session getSession();

    void setSession(Session session);
}
