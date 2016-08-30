package com.openhabbo.api.game.items.data.stuff.types;

import com.openhabbo.api.game.items.data.stuff.StuffData;
import com.openhabbo.api.game.items.data.stuff.StuffDataType;

public class LegacyStuffData extends StuffData {
    private String legacyString;

    public LegacyStuffData(String data) {
        this.legacyString = data;
    }

    public StuffDataType getType() {
        return StuffDataType.LEGACY;
    }

    public String getLegacyString() {
        return this.legacyString;
    }
}
