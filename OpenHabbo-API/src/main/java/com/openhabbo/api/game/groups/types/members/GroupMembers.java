package com.openhabbo.api.game.groups.types.members;

import com.openhabbo.api.util.Disposable;

import java.util.Set;

public interface GroupMembers extends Disposable {
    void createMembership(GroupMember member);

    void removeMembership(int playerId);

    void createMembershipRequest(int playerId);

    void clearRequests();

    void removeRequest(int playerId);

    boolean hasMembership(int playerId);

    Set<GroupMember> getAll();
}
