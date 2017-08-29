package com.openhabbo.core.protocol.headers;

import com.google.inject.Singleton;
import com.openhabbo.api.networking.communication.protocol.MessageEvent;
import com.openhabbo.api.networking.communication.protocol.headers.IncomingHeaderProvider;
import com.openhabbo.core.protocol.events.handshake.GenerateSecretKeyMessageEvent;
import com.openhabbo.core.protocol.events.handshake.GetClientVersionMessageEvent;
import com.openhabbo.core.protocol.events.handshake.InitCryptoMessageEvent;
import com.openhabbo.core.protocol.events.handshake.SSOTicketMessageEvent;
import com.openhabbo.core.protocol.parsers.handshake.GenerateSecretKeyMessageParser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class EventHeaderProvider implements IncomingHeaderProvider {

    private final Map<Short, Class<? extends MessageEvent>> eventClasses;

    public EventHeaderProvider() {
        this.eventClasses = new ConcurrentHashMap<>();

        // TODO: Put this in a JSON file or something
        this.eventClasses.put((short) 4000, GetClientVersionMessageEvent.class);
        this.eventClasses.put((short) 1905, InitCryptoMessageEvent.class);
        this.eventClasses.put((short) 1127, GenerateSecretKeyMessageEvent.class);
        this.eventClasses.put((short) 2190, SSOTicketMessageEvent.class);
    }

    @Override
    public Class<? extends MessageEvent> getEventClass(short eventId) {
        return this.eventClasses.get(eventId);
    }
}
