package com.openhabbo.core.sessions;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.openhabbo.api.game.players.Player;
import com.openhabbo.api.modules.events.SystemEventRegistry;
import com.openhabbo.api.modules.events.sessions.SessionActiveEvent;
import com.openhabbo.api.modules.events.sessions.args.SessionEventArgs;
import com.openhabbo.api.networking.communication.ChannelProxy;
import com.openhabbo.api.networking.communication.protocol.encryption.KeyStore;
import com.openhabbo.api.networking.sessions.Session;
import com.openhabbo.api.networking.sessions.SessionMessageHandler;
import com.openhabbo.core.protocol.encryption.DiffieHellman;
import com.openhabbo.core.protocol.events.handshake.GetClientVersionMessageEvent;
import com.openhabbo.core.sessions.messaging.CoreMessageHandler;
import com.openhabbo.core.sessions.messaging.handshake.ClientVersionMessageConsumer;

import java.util.UUID;

public class CoreSession implements Session {
    private final UUID sessionId;

    private SystemEventRegistry systemEventRegistry;
    private SessionMessageHandler messageHandler;

    private KeyStore keyStore;
    private ChannelProxy channelProxy;
    private Player player;

    private boolean initialised = false;

    @Inject
    public CoreSession(@Assisted UUID id, @Assisted ChannelProxy channelProxy, SystemEventRegistry systemEventRegistry, SessionMessageHandler sessionMessageHandler) {
        this.sessionId = id;
        this.channelProxy = channelProxy;
        this.systemEventRegistry = systemEventRegistry;
        this.messageHandler = sessionMessageHandler;
    }

    @Override
    public void onConnectionInitialised() {
        this.initialised = true;

        // Initialise the required components so the session can communicate with the Habbo client
        this.messageHandler.setSession(this);
        this.keyStore = new DiffieHellman();

        this.systemEventRegistry.invoke(SessionActiveEvent.class, new SessionEventArgs(this));
        this.messageHandler.registerEvent(new GetClientVersionMessageEvent(ClientVersionMessageConsumer.class));
    }

    @Override
    public ChannelProxy getChannel() {
        return this.channelProxy;
    }

    @Override
    public void dispose() {
        this.messageHandler.dispose();

        if (this.player != null) {
            this.player.dispose();
        }
    }

    @Override
    public void disconnect() {
        this.channelProxy.close();
    }

    @Override
    public UUID getId() {
        return this.sessionId;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean isInitialised() {
        return this.initialised;
    }

    @Override
    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    @Override
    public SessionMessageHandler getMessageHandler() {
        return this.messageHandler;
    }
}