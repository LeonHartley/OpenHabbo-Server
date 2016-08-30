package com.openhabbo.api.game.groups;

import com.openhabbo.api.game.groups.types.Group;

public interface GroupService {
    Group getGroupById(int id);

    Group getGroupByRoomId(int id);
}
