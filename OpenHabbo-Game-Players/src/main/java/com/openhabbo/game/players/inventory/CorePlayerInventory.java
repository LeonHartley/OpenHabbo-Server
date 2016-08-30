package com.openhabbo.game.players.inventory;

import com.google.inject.Inject;
import com.openhabbo.api.game.players.inventory.PlayerInventory;
import com.openhabbo.api.game.players.inventory.types.BadgeInventoryItem;
import com.openhabbo.api.game.players.inventory.types.FurnitureInventoryItem;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CorePlayerInventory implements PlayerInventory {

    @Inject
    public CorePlayerInventory() {

    }

    @Override
    public Map<UUID, FurnitureInventoryItem> getFurnitureItems() {
        return null;
    }

    @Override
    public Set<BadgeInventoryItem> getBadges() {
        return null;
    }
}
