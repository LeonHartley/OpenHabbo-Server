package com.openhabbo.core.storage.dao.items.beans;

import com.openhabbo.api.game.items.data.FurnitureDefinition;

public class FurnitureDefinitionBean implements FurnitureDefinition {
    private final int id;
    private final String publicName;
    private final String itemName;
    private final String type;
    private final int width;
    private final int length;
    private final double height;
    private final int spriteId;

    private final boolean canStack;
    private final boolean canSit;
    private final boolean canWalk;
    private final boolean canTrade;
    private final boolean canRecycle;
    private final boolean canMarket;
    private final boolean canGift;
    private final boolean canInventoryStack;

    private final int effectId;
    private final int offerId;
    private final String interactionAlias;
    private final int stateCount;
    private final Integer[] vendingIds;
    private final boolean requiresRights;

    private final int songId;

    private final Double[] variableHeights;

    public FurnitureDefinitionBean(int id, String publicName, String itemName, String type, int width, int length, double height, int spriteId, boolean canStack, boolean canSit, boolean canWalk, boolean canTrade, boolean canRecycle, boolean canMarket, boolean canGift, boolean canInventoryStack, int effectId, int offerId, String interactionAlias, int stateCount, Integer[] vendingIds, boolean requiresRights, int songId, Double[] variableHeights) {
        this.id = id;
        this.publicName = publicName;
        this.itemName = itemName;
        this.type = type;
        this.width = width;
        this.length = length;
        this.height = height;
        this.spriteId = spriteId;
        this.canStack = canStack;
        this.canSit = canSit;
        this.canWalk = canWalk;
        this.canTrade = canTrade;
        this.canRecycle = canRecycle;
        this.canMarket = canMarket;
        this.canGift = canGift;
        this.canInventoryStack = canInventoryStack;
        this.effectId = effectId;
        this.offerId = offerId;
        this.interactionAlias = interactionAlias;
        this.stateCount = stateCount;
        this.vendingIds = vendingIds;
        this.requiresRights = requiresRights;
        this.songId = songId;
        this.variableHeights = variableHeights;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getPublicName() {
        return publicName;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public int getSpriteId() {
        return spriteId;
    }

    @Override
    public boolean canStack() {
        return this.canStack;
    }

    @Override
    public boolean isSittable() {
        return canSit;
    }

    @Override
    public boolean isWalkable() {
        return canWalk;
    }

    @Override
    public boolean isTradable() {
        return canTrade;
    }

    @Override
    public boolean isRecyclable() {
        return canRecycle;
    }

    @Override
    public boolean isMarketable() {
        return canMarket;
    }

    @Override
    public boolean isGiftable() {
        return canGift;
    }

    @Override
    public boolean stacksInInventory() {
        return canInventoryStack;
    }

    @Override
    public int getEffectId() {
        return effectId;
    }

    @Override
    public int getOfferId() {
        return offerId;
    }

    @Override
    public String getInteractionAlias() {
        return interactionAlias;
    }

    @Override
    public int getStateCount() {
        return stateCount;
    }

    @Override
    public Integer[] getVendingIds() {
        return vendingIds;
    }

    @Override
    public boolean requiresRightsForInteraction() {
        return requiresRights;
    }

    @Override
    public int getSongId() {
        return songId;
    }

    @Override
    public Double[] getVariableHeights() {
        return variableHeights;
    }
}
