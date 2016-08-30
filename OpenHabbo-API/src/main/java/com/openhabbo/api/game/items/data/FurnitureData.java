package com.openhabbo.api.game.items.data;

import com.openhabbo.api.game.items.data.stuff.StuffData;

import javax.xml.crypto.Data;
import java.util.UUID;

public interface FurnitureData<T extends StuffData> {
    UUID getId();

    FurnitureDefinition getDefinition();

    T getData();

    UUID getPlayerId();
}
