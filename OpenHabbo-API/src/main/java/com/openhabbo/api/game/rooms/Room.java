package com.openhabbo.api.game.rooms;

import com.openhabbo.api.attributes.Attributable;
import com.openhabbo.api.game.rooms.processor.RoomProcessor;
import com.openhabbo.api.modules.events.EventRegistry;
import com.openhabbo.api.game.rooms.avatars.RoomAvatars;

public interface Room extends Attributable {
    int getId();

    RoomAvatars getAvatars();

    RoomProcessor getProcessor();
}
