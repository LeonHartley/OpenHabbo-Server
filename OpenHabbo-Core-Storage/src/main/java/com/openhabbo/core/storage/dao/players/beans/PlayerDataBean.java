package com.openhabbo.core.storage.dao.players.beans;

import com.openhabbo.api.game.players.PlayerData;
import com.openhabbo.api.game.players.data.PlayerGender;

public class PlayerDataBean implements PlayerData {

    private final int id;
    private String username;
    private String figure;
    private PlayerGender gender;
    private String motto;
    private int rank;

    public PlayerDataBean(int id, String username, String figure, PlayerGender gender, String motto, int rank) {
        this.id = id;
        this.username = username;
        this.figure = figure;
        this.gender = gender;
        this.motto = motto;
        this.rank = rank;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getFigure() {
        return figure;
    }

    @Override
    public void setFigure(String figure) {
        this.figure = figure;
    }

    @Override
    public PlayerGender getGender() {
        return gender;
    }

    @Override
    public void setGender(PlayerGender gender) {
        this.gender = gender;
    }

    @Override
    public String getMotto() {
        return motto;
    }

    @Override
    public void setMotto(String motto) {
        this.motto = motto;
    }

    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    }
}
