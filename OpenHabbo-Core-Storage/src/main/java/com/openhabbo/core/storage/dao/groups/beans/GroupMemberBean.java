package com.openhabbo.core.storage.dao.groups.beans;

import com.openhabbo.api.game.groups.types.members.GroupAccessLevel;
import com.openhabbo.api.game.groups.types.members.GroupMember;

public class GroupMemberBean implements GroupMember {
    private final int membershipId;
    private final int playerId;
    private final int groupId;

    private GroupAccessLevel groupAccessLevel;

    private int dateJoined;

    public GroupMemberBean(int membershipId, int playerId, int groupId, GroupAccessLevel groupAccessLevel, int dateJoined) {
        this.membershipId = membershipId;
        this.playerId = playerId;
        this.groupId = groupId;
        this.groupAccessLevel = groupAccessLevel;
        this.dateJoined = dateJoined;
    }

    @Override
    public int getMembershipId() {
        return this.membershipId;
    }

    @Override
    public int getPlayerId() {
        return this.playerId;
    }

    @Override
    public int getGroupId() {
        return this.groupId;
    }

    @Override
    public GroupAccessLevel getAccessLevel() {
        return this.groupAccessLevel;
    }

    @Override
    public int getDateJoined() {
        return this.dateJoined;
    }
}
