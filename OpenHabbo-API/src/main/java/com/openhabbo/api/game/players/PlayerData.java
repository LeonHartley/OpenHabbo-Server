package com.openhabbo.api.game.players;

import com.openhabbo.api.game.players.data.PlayerGender;

public interface PlayerData {
    Integer getId();

    String getUsername();

    void setUsername(String username);

    String getFigure();

    void setFigure(String figure);

    PlayerGender getGender();

    void setGender(PlayerGender gender);

    String getMotto();

    void setMotto(String motto);

    int getRank();

    void setRank(int rank);
}
