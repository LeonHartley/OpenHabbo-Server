package com.openhabbo.api.game.players;

import com.openhabbo.api.networking.sessions.Session;

public interface PlayerFactory {
    Player create(Session session, PlayerData playerData);
}
