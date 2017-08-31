package com.openhabbo.api.storage.dao.items;

import com.openhabbo.api.game.items.data.FurnitureDefinition;

import java.util.Map;
import java.util.function.Consumer;

public interface FurnitureDefinitionDao {
    void getAllDefinitions(Consumer<Map<Integer, FurnitureDefinition>> onComplete);
}
