package com.openhabbo.api.game.rooms.avatars.effects;

public class RoomEffect {
    private final int effectId;
    private final int expireMilliseconds;

    public RoomEffect(int effectId, int expireMilliseconds) {
        this.effectId = effectId;
        this.expireMilliseconds = expireMilliseconds;
    }

    public int getEffectId() {
        return effectId;
    }

    public int getExpireTimestamp() {
        return expireMilliseconds;
    }
}
