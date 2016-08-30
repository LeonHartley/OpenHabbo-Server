package com.openhabbo.game.groups.types;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.openhabbo.api.game.groups.types.Group;
import com.openhabbo.api.game.groups.types.GroupData;
import com.openhabbo.api.game.groups.types.members.GroupMembers;
import com.openhabbo.api.storage.dao.groups.GroupDao;
import com.openhabbo.core.caching.CacheableObject;
import com.openhabbo.game.groups.types.components.MemberComponent;

public class GroupInstance extends CacheableObject implements Group {
    private final GroupData groupData;
    private final GroupMembers groupMembers;

    @Inject
    public GroupInstance(@Assisted GroupData groupData, GroupDao groupDao) {
        this.groupData = groupData;

        this.groupMembers = new MemberComponent(groupData, groupDao);
    }

    @Override
    public GroupData getData() {
        return this.groupData;
    }

    @Override
    public GroupMembers getMembers() {
        return this.groupMembers;
    }

    @Override
    public void dispose() {
        this.groupMembers.dispose();
    }
}
