package com.openhabbo.api.game.groups.types.members;

public interface GroupMember {
    int getMembershipId();

    int getPlayerId();

    int getGroupId();

    GroupAccessLevel getAccessLevel();

    int getDateJoined();
}
