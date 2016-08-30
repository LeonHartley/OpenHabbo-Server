package com.openhabbo.api.game.bots.data;

import com.openhabbo.api.game.players.data.PlayerGender;

public interface BotData {
    String getName();

    String getFigure();

    PlayerGender getGender();
}
