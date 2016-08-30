package com.openhabbo.core.storage.dao.navigator;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.openhabbo.api.game.navigator.categories.NavigatorCategory;
import com.openhabbo.api.game.navigator.categories.NavigatorCategoryType;
import com.openhabbo.api.game.navigator.categories.NavigatorViewMode;
import com.openhabbo.api.storage.dao.navigator.NavigatorCategoryDao;
import com.openhabbo.api.threading.EventScheduler;
import com.openhabbo.core.storage.ConnectionProvider;
import com.openhabbo.core.storage.Transaction;
import com.openhabbo.core.storage.dao.AbstractDao;
import com.openhabbo.core.storage.dao.DaoEventScheduler;
import com.openhabbo.core.storage.dao.navigator.beans.NavigatorCategoryBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class CoreNavigatorCategoryDao extends AbstractDao implements NavigatorCategoryDao {
    private static final Logger log = LogManager.getLogger(CoreNavigatorCategoryDao.class);

    @Inject
    public CoreNavigatorCategoryDao(EventScheduler eventScheduler, DaoEventScheduler daoEventScheduler, ConnectionProvider connectionProvider) {
        super(eventScheduler, daoEventScheduler, connectionProvider);
    }

    @Override
    public Map<Integer, NavigatorCategory> getAllCategories() {
        Map<Integer, NavigatorCategory> categories = Maps.newHashMap();

//        try (Transaction transaction = this.createTransaction("SELECT * FROM navigator_categories WHERE enabled = '1' ORDER BY order_id ASC")) {
//            while (transaction.getResults().hasResults()) {
//                final NavigatorCategory navigatorCategory = new NavigatorCategoryBean(
//                        transaction.getResults().getInteger("id"),
//                        transaction.getResults().getString("category"),
//                        transaction.getResults().getString("category_identifier"),
//                        NavigatorCategoryType.valueOf(transaction.getResults().getString("category_type").toUpperCase()),
//                        transaction.getResults().getString("public_name"),
//                        transaction.getResults().getInteger("required_rank"),
//                        NavigatorViewMode.valueOf(transaction.getResults().getString("view_mode").toUpperCase()),
//                        transaction.getResults().getString("visible").equals("1"),
//                        transaction.getResults().getInteger("room_count"),
//                        transaction.getResults().getInteger("room_count_expanded"));
//
//                categories.put(navigatorCategory.getId(), navigatorCategory);
//            }
//        } catch (Exception e) {
//            log.error("Failed to execute transaction: NavigatorCategoryDao.getAllCategories", e);
//        }

        return categories;
    }
}
