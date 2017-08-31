package com.openhabbo.api.game.rooms.avatars;

public enum AvatarStatus {
    MOVE("mv"),
    LAY("lay"),
    SIT("sit");

    private String status;

    AvatarStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
