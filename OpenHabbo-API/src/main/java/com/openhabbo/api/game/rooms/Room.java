package com.openhabbo.api.game.rooms;

import com.openhabbo.api.modules.events.EventRegistry;
import com.openhabbo.api.game.rooms.avatars.RoomAvatars;

public interface Room {
    int getId();

    RoomAvatars getAvatars();
}
