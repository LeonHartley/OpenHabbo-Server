package com.openhabbo.core.storage.dao.groups.beans;

import com.openhabbo.api.game.groups.types.GroupData;
import com.openhabbo.api.game.groups.types.GroupType;

public class GroupDataBean implements GroupData {
    private final int id;
    private String groupName;
    private String groupDescription;
    private String groupBadge;

    private int ownerId;
    private int roomId;
    private GroupType type;

    private int colour1;
    private int colour2;

    private boolean canMembersDecorateRoom;
    private boolean hasForum;

    public GroupDataBean(int id, String name, String description, String badge, int ownerId, int roomId, GroupType groupType, int colour1, int colour2, boolean canMembersDecorate, boolean hasForum) {
        this.id = id;
        this.groupName = name;
        this.groupDescription = description;
        this.groupBadge = badge;
        this.ownerId = ownerId;
        this.roomId = roomId;
        this.type = groupType;
        this.colour1 = colour1;
        this.colour2 = colour2;
        this.canMembersDecorateRoom = canMembersDecorate;
        this.hasForum = hasForum;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public String getGroupDescription() {
        return groupDescription;
    }

    @Override
    public String getGroupBadge() {
        return groupBadge;
    }

    @Override
    public int getOwnerId() {
        return ownerId;
    }

    @Override
    public int getRoomId() {
        return roomId;
    }

    @Override
    public GroupType getGroupType() {
        return type;
    }

    @Override
    public int getColour1() {
        return colour1;
    }

    @Override
    public int getColour2() {
        return colour2;
    }

    @Override
    public boolean canMembersDecorateRoom() {
        return canMembersDecorateRoom;
    }

    @Override
    public boolean hasGroupForum() {
        return hasForum;
    }
}
