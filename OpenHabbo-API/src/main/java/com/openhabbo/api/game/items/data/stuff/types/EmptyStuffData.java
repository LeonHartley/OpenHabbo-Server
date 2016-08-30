package com.openhabbo.api.game.items.data.stuff.types;

import com.openhabbo.api.game.items.data.stuff.StuffData;
import com.openhabbo.api.game.items.data.stuff.StuffDataType;

public class EmptyStuffData extends StuffData {
    public StuffDataType getType() {
        return StuffDataType.EMPTY;
    }
}
