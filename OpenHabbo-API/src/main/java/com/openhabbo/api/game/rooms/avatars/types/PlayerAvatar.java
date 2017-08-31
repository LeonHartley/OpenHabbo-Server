package com.openhabbo.api.game.rooms.avatars.types;

import com.openhabbo.api.game.players.Player;
import com.openhabbo.api.game.rooms.avatars.Avatar;
import com.openhabbo.api.game.rooms.avatars.MobileAvatar;
import com.openhabbo.api.game.rooms.avatars.effects.RoomEffect;
import com.openhabbo.api.game.rooms.avatars.effects.RoomEffectType;

public interface PlayerAvatar extends Avatar, MobileAvatar {
    Player getPlayer();
}
