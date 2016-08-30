package com.openhabbo.api.game.groups;

import com.openhabbo.api.game.groups.types.Group;
import com.openhabbo.api.game.groups.types.GroupData;

public interface GroupFactory {
    Group create(GroupData data);
}
