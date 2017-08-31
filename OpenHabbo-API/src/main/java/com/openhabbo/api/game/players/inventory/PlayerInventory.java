package com.openhabbo.api.game.players.inventory;

import com.openhabbo.api.game.players.inventory.types.BadgeInventoryItem;
import com.openhabbo.api.game.players.inventory.types.FurnitureInventoryItem;
import com.openhabbo.api.util.Disposable;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface PlayerInventory extends Disposable {
    Map<UUID, FurnitureInventoryItem> getFurnitureItems();

    void addItem(FurnitureInventoryItem inventoryItem);

    void removeItem(UUID id);

    void clear();

    Set<BadgeInventoryItem> getBadges();
}
