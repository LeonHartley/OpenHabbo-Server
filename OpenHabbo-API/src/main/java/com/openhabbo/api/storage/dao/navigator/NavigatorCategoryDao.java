package com.openhabbo.api.storage.dao.navigator;

import com.openhabbo.api.game.navigator.categories.NavigatorCategory;
import com.openhabbo.api.storage.dao.Dao;

import java.util.Map;

public interface NavigatorCategoryDao {
    Map<Integer, NavigatorCategory> getAllCategories();
}
