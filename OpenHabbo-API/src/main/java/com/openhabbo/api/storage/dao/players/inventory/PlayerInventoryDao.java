package com.openhabbo.api.storage.dao.players.inventory;

import com.openhabbo.api.game.players.inventory.types.FurnitureInventoryItem;

import java.util.Map;
import java.util.UUID;

public interface PlayerInventoryDao {
    void getFurnitureInventory(int playerId, Map<UUID, FurnitureInventoryItem> onComplete);
}
