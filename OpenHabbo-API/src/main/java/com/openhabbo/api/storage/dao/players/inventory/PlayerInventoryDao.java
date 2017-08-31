package com.openhabbo.api.storage.dao.players.inventory;

import com.openhabbo.api.game.players.inventory.types.FurnitureInventoryItem;

import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public interface PlayerInventoryDao {
    void getFurnitureInventory(int playerId, Consumer<Map<UUID, FurnitureInventoryItem>> onComplete);
}
