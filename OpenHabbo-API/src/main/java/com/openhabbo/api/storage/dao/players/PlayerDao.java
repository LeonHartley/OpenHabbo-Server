package com.openhabbo.api.storage.dao.players;

import com.openhabbo.api.game.players.PlayerData;
import com.openhabbo.api.storage.dao.Dao;

import java.util.function.Consumer;

public interface PlayerDao extends Dao<PlayerData, Integer> {
    void getDataByTicket(final String ssoTicket, Consumer<PlayerData> consumer);
}
