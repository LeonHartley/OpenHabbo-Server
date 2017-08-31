package com.openhabbo.api.storage.dao.navigator;

import com.openhabbo.api.game.navigator.categories.NavigatorCategory;
import com.openhabbo.api.storage.dao.Dao;

import java.util.Map;
import java.util.function.Consumer;

public interface NavigatorCategoryDao {
    void getAllCategories(Consumer<Map<Integer, NavigatorCategory>> onComplete);
}
