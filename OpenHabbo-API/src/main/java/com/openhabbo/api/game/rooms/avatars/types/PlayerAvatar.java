package com.openhabbo.api.game.rooms.avatars.types;

import com.openhabbo.api.game.players.Player;
import com.openhabbo.api.game.rooms.avatars.Avatar;
import com.openhabbo.api.game.rooms.avatars.MobileAvatar;

public interface PlayerAvatar extends Avatar, MobileAvatar {
    Player getPlayer();
}
