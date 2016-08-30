package com.openhabbo.api.storage.dao.items;

import com.openhabbo.api.game.items.data.FurnitureDefinition;

import java.util.Map;

public interface FurnitureDefinitionDao {
    Map<Integer, FurnitureDefinition> getAllDefinitions();
}
