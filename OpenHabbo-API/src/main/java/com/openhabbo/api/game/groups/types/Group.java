package com.openhabbo.api.game.groups.types;

import com.openhabbo.api.game.groups.types.members.GroupMembers;

public interface Group {
    GroupData getData();

    GroupMembers getMembers();
}
