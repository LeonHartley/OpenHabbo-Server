package com.openhabbo.api.game.players.inventory;

import com.openhabbo.api.game.players.Player;

public interface PlayerInventoryFactory {
    PlayerInventory create(Player player);
}
