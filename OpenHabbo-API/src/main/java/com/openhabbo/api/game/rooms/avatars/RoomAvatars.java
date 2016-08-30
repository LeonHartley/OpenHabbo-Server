package com.openhabbo.api.game.rooms.avatars;

import com.openhabbo.api.game.rooms.RoomComponent;
import com.openhabbo.api.game.rooms.avatars.types.BotAvatar;
import com.openhabbo.api.game.rooms.avatars.types.PlayerAvatar;

import java.util.Map;

public interface RoomAvatars extends RoomComponent {
    PlayerAvatar getPlayerAvatarByPlayerId(int playerId);

    BotAvatar getBotAvatarByBotId(int botId);

    Avatar getAvatarByVirtualId(int virtualId);

    Map<Integer, Avatar> getAllAvatars();

    Map<Integer, PlayerAvatar> getAllPlayerAvatars();

    Map<Integer, BotAvatar> getAllBotAvatars();
}
