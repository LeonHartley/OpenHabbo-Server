package com.openhabbo.game.navigator;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.api.game.navigator.categories.NavigatorCategory;
import com.openhabbo.api.game.navigator.categories.NavigatorCategoryService;
import com.openhabbo.api.storage.dao.navigator.NavigatorCategoryDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Singleton
public class CoreNavigatorCategoryService implements NavigatorCategoryService {
    private static final Logger log = LogManager.getLogger(CoreNavigatorCategoryService.class);
    private final Map<Integer, NavigatorCategory> navigatorCategories;

    @Inject
    public CoreNavigatorCategoryService(NavigatorCategoryDao navigatorCategoryDao) {
        this.navigatorCategories = navigatorCategoryDao.getAllCategories();

        log.info("Loaded {} navigator categories", this.navigatorCategories.size());
    }

    @Override
    public Map<Integer, NavigatorCategory> getCategories() {
        return this.navigatorCategories;
    }
}
