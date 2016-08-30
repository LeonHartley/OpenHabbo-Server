package com.openhabbo.api.game.items.instances;

import com.openhabbo.api.game.items.data.FurnitureData;
import com.openhabbo.api.game.items.data.stuff.StuffData;
import com.openhabbo.api.game.items.events.BasicItemConstraints;
import com.openhabbo.api.game.items.events.BasicItemEvents;
import com.openhabbo.api.game.rooms.util.Position;

public interface Item<T extends StuffData> extends FurnitureData<T>, BasicItemEvents, BasicItemConstraints {
    Position getPosition();
}
