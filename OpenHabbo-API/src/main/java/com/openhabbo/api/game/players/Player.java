package com.openhabbo.api.game.players;

import com.openhabbo.api.game.players.inventory.PlayerInventory;
import com.openhabbo.api.game.rooms.Room;
import com.openhabbo.api.game.rooms.avatars.types.PlayerAvatar;
import com.openhabbo.api.networking.sessions.Session;
import com.openhabbo.api.util.Disposable;

public interface Player extends Disposable {
    PlayerAvatar getAvatar();

    void setAvatar(PlayerAvatar playerAvatar);

    PlayerData getData();

    PlayerInventory getInventory();

    Session getSession();
}
