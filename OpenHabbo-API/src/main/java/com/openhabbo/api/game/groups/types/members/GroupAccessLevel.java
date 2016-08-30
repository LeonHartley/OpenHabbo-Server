package com.openhabbo.api.game.groups.types.members;

public enum GroupAccessLevel {
    MEMBER, ADMIN, OWNER;

    public boolean canControlGroup() {
        return this.equals(ADMIN) || this.equals(OWNER);
    }
}
