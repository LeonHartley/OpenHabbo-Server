package com.openhabbo.api.networking.sessions;

import com.openhabbo.api.game.players.Player;
import com.openhabbo.api.networking.communication.ChannelProxy;
import com.openhabbo.api.modules.events.EventRegistry;
import com.openhabbo.api.networking.communication.protocol.encryption.KeyStore;

import java.util.UUID;

public interface Session {
    UUID getId();

    ChannelProxy getChannel();

    Player getPlayer();

    void onConnectionInitialised();

    void setPlayer(Player player);

    SessionMessageHandler getMessageHandler();

    void dispose();

    void disconnect();

    boolean isInitialised();

    KeyStore getKeyStore();
}
