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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CoreNavigatorCategoryDao extends AbstractDao implements NavigatorCategoryDao {
    private static final Logger log = LogManager.getLogger(CoreNavigatorCategoryDao.class);

    @Inject
    public CoreNavigatorCategoryDao(EventScheduler eventScheduler, DaoEventScheduler daoEventScheduler, ConnectionProvider connectionProvider) {
        super(eventScheduler, daoEventScheduler, connectionProvider);
    }

    @Override
    public void getAllCategories(Consumer<Map<Integer, NavigatorCategory>> onComplete) {
        final Transaction transaction = this.createTransaction(Transaction.Type.SELECT, "SELECT * FROM navigator_categories WHERE enabled = '1' ORDER BY order_id ASC");

        this.queueTransaction(transaction, (results) -> {
            final Map<Integer, NavigatorCategory> categories = new HashMap<>();

            try {
                while (results.hasResults()) {
                    final NavigatorCategory navigatorCategory = new NavigatorCategoryBean(
                            results.getInteger("id"),
                            results.getString("category"),
                            results.getString("category_identifier"),
                            NavigatorCategoryType.valueOf(results.getString("category_type").toUpperCase()),
                            results.getString("public_name"),
                            results.getInteger("required_rank"),
                            NavigatorViewMode.valueOf(results.getString("view_mode").toUpperCase()),
                            results.getString("visible").equals("1"),
                            results.getInteger("room_count"),
                            results.getInteger("room_count_expanded"));

                    categories.put(navigatorCategory.getId(), navigatorCategory);
                }
            } catch (Exception e) {
                log.error("Failed to execute transaction: NavigatorCategoryDao.getAllCategories", e);
            }

            onComplete.accept(categories);
        });
    }
}
