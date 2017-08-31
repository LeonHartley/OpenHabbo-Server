package com.openhabbo.game.players.inventory;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.openhabbo.api.game.players.Player;
import com.openhabbo.api.game.players.inventory.PlayerInventory;
import com.openhabbo.api.game.players.inventory.types.BadgeInventoryItem;
import com.openhabbo.api.game.players.inventory.types.FurnitureInventoryItem;
import com.openhabbo.api.storage.dao.players.inventory.PlayerInventoryDao;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CorePlayerInventory implements PlayerInventory {

    private final Player player;

    private final Map<UUID, FurnitureInventoryItem> inventoryItems;

    @Inject
    public CorePlayerInventory(@Assisted Player player, PlayerInventoryDao inventoryDao) {
        this.player = player;

        this.inventoryItems = Maps.newConcurrentMap();

        inventoryDao.getFurnitureInventory(this.player.getData().getId(), this.inventoryItems::putAll);
    }

    @Override
    public Map<UUID, FurnitureInventoryItem> getFurnitureItems() {
        return null;
    }

    @Override
    public void addItem(FurnitureInventoryItem inventoryItem) {
        this.inventoryItems.put(inventoryItem.getId(), inventoryItem);
        // sync with client?
    }

    @Override
    public void removeItem(UUID id) {
        this.inventoryItems.remove(id);
        // sync with client?
    }

    @Override
    public void clear() {
        this.inventoryItems.clear();
        // send packet?
    }

    @Override
    public Set<BadgeInventoryItem> getBadges() {
        return null;
    }

    @Override
    public void dispose() {
        this.inventoryItems.clear();
    }
}
