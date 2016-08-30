package com.openhabbo.core.storage.dao.navigator.beans;

import com.openhabbo.api.game.navigator.categories.NavigatorCategory;
import com.openhabbo.api.game.navigator.categories.NavigatorCategoryType;
import com.openhabbo.api.game.navigator.categories.NavigatorViewMode;

public class NavigatorCategoryBean implements NavigatorCategory {
    private final int id;
    private final String category;
    private final String categoryId;
    private final NavigatorCategoryType type;
    private final String publicName;
    private final int requiredRank;
    private final NavigatorViewMode viewMode;
    private final boolean isVisible;
    private final int roomCount;
    private final int roomCountExpanded;

    public NavigatorCategoryBean(int id, String category, String categoryId, NavigatorCategoryType type, String publicName, int requiredRank, NavigatorViewMode viewMode, boolean isVisible, int roomCount, int roomCountExpanded) {
        this.id = id;
        this.category = category;
        this.categoryId = categoryId;
        this.type = type;
        this.publicName = publicName;
        this.requiredRank = requiredRank;
        this.viewMode = viewMode;
        this.isVisible = isVisible;
        this.roomCount = roomCount;
        this.roomCountExpanded = roomCountExpanded;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getCategoryId() {
        return categoryId;
    }

    @Override
    public NavigatorCategoryType getCategoryType() {
        return type;
    }

    @Override
    public String getPublicName() {
        return publicName;
    }

    @Override
    public int getRequiredRank() {
        return requiredRank;
    }

    @Override
    public NavigatorViewMode getViewMode() {
        return viewMode;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public int getRoomCount() {
        return roomCount;
    }

    @Override
    public int getRoomCountExpanded() {
        return roomCountExpanded;
    }
}
