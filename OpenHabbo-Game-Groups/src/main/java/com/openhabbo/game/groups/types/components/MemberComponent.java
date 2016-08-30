package com.openhabbo.game.groups.types.components;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.openhabbo.api.game.groups.types.GroupData;
import com.openhabbo.api.game.groups.types.members.GroupMember;
import com.openhabbo.api.game.groups.types.members.GroupMembers;
import com.openhabbo.api.storage.dao.groups.GroupDao;

import java.util.Set;

public class MemberComponent implements GroupMembers {

    private int groupId;
    private final GroupDao groupDao;
    private Set<GroupMember> groupMembers;

    public MemberComponent(GroupData groupData, GroupDao groupDao) {
        this.groupDao = groupDao;
        this.groupId = groupData.getId();

        this.groupMembers = groupDao.getMembersForGroup(this.groupId);
    }

    @Override
    public void createMembership(GroupMember member) {

    }

    @Override
    public void removeMembership(int playerId) {

    }

    @Override
    public void createMembershipRequest(int playerId) {

    }

    @Override
    public void clearRequests() {

    }

    @Override
    public void removeRequest(int playerId) {

    }

    @Override
    public boolean hasMembership(int playerId) {
        return false;
    }

    @Override
    public Set<GroupMember> getAll() {
        return null;
    }

    @Override
    public void dispose() {
        this.groupMembers.clear();
    }
}
