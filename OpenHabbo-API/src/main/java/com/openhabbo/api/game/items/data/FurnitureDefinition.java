package com.openhabbo.api.game.items.data;

import com.openhabbo.api.storage.dao.Dao;

import java.util.List;

public interface FurnitureDefinition {
    int getId();

    String getPublicName();

    String getItemName();

    String getType();

    int getWidth();

    int getLength();

    double getHeight();

    int getSpriteId();

    boolean canStack();

    /**
     * TODO: change this to a "walk action", so i can set specific status' when entities step on the item depending on the item or something
     */
    boolean isSittable();

    /**
     * TODO: Same as above
     */
    boolean isWalkable();

    boolean isTradable();

    boolean isRecyclable();

    boolean isMarketable();

    boolean isGiftable();

    boolean stacksInInventory();

    int getEffectId();

    int getOfferId();

    String getInteractionAlias();

    int getStateCount();

    Integer[] getVendingIds();

    boolean requiresRightsForInteraction();

    Double[] getVariableHeights();

    int getSongId();
}
