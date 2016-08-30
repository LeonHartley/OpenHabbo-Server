package com.openhabbo.api.storage.dao.groups;

import com.openhabbo.api.game.groups.types.GroupData;
import com.openhabbo.api.game.groups.types.members.GroupMember;

import java.util.Set;

public interface GroupDao {
    GroupData getDataById(int id);

    Set<GroupMember> getMembersForGroup(int id);
}
