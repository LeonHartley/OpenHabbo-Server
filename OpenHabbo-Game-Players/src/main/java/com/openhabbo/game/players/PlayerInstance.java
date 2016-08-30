package com.openhabbo.game.players;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.openhabbo.api.game.players.Player;
import com.openhabbo.api.game.players.PlayerData;
import com.openhabbo.api.game.players.inventory.PlayerInventory;
import com.openhabbo.api.game.rooms.avatars.types.PlayerAvatar;
import com.openhabbo.api.networking.sessions.Session;

public class PlayerInstance implements Player {

    private final Session session;
    private final PlayerData playerData;

    private PlayerAvatar playerAvatar;

    @Inject
    public PlayerInstance(@Assisted Session session, @Assisted PlayerData playerData) {
        this.session = session;
        this.playerData = playerData;
    }

    @Override
    public PlayerAvatar getAvatar() {
        return this.playerAvatar;
    }

    @Override
    public void setAvatar(PlayerAvatar playerAvatar) {
        this.playerAvatar = playerAvatar;
    }

    @Override
    public PlayerData getData() {
        return this.playerData;
    }

    @Override
    public PlayerInventory getInventory() {
        return null;
    }

    @Override
    public Session getSession() {
        return this.session;
    }

    @Override
    public void dispose() {
        if(this.getAvatar() != null) {
            this.getAvatar().dispose();
        }
    }
}
