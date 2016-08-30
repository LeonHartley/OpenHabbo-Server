package com.openhabbo.api.game.players.inventory.types;

import com.openhabbo.api.game.players.inventory.InventoryItem;
import com.openhabbo.api.game.players.inventory.types.badges.BadgeType;

public interface BadgeInventoryItem extends InventoryItem {
    String getBadgeId();

    Integer getSlotId();

    Long getTimeAwarded();

    Integer getAwardingPlayer();

    BadgeType getBadgeType();
}
