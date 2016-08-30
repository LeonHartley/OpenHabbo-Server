package com.openhabbo.api.game.navigator.categories;

public interface NavigatorCategory {
    int getId();

    String getCategory();

    String getCategoryId();

    NavigatorCategoryType getCategoryType();

    String getPublicName();

    int getRequiredRank();

    NavigatorViewMode getViewMode();

    boolean isVisible();

    int getRoomCount();

    int getRoomCountExpanded();
}
