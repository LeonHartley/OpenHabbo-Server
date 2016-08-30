package com.openhabbo.api.game.rooms.avatars.types;

import com.openhabbo.api.game.bots.data.BotData;
import com.openhabbo.api.game.bots.data.ai.BotAI;
import com.openhabbo.api.game.rooms.avatars.Avatar;
import com.openhabbo.api.game.rooms.avatars.MobileAvatar;

public interface BotAvatar extends Avatar, MobileAvatar {
    BotAI getBotAI();

    BotData getBotData();
}
