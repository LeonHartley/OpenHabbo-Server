package com.openhabbo.api.game.players.inventory;

import com.openhabbo.api.game.players.inventory.types.BadgeInventoryItem;
import com.openhabbo.api.game.players.inventory.types.FurnitureInventoryItem;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface PlayerInventory {
    Map<UUID, FurnitureInventoryItem> getFurnitureItems();

    Set<BadgeInventoryItem> getBadges();
}
