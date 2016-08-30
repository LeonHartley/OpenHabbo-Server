package com.openhabbo.api.game.groups.types;

public interface GroupData {
    int getId();

    String getGroupName();

    String getGroupDescription();

    String getGroupBadge();

    int getOwnerId();

    int getRoomId();

    GroupType getGroupType();

    int getColour1();

    int getColour2();

    boolean canMembersDecorateRoom();

    boolean hasGroupForum();
}
