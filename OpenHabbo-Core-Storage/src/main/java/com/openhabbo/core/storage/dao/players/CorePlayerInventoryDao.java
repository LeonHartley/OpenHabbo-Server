package com.openhabbo.core.storage.dao.players;

import com.openhabbo.api.game.players.data.PlayerGender;
import com.openhabbo.api.game.players.inventory.types.FurnitureInventoryItem;
import com.openhabbo.api.storage.dao.players.inventory.PlayerInventoryDao;
import com.openhabbo.api.threading.EventScheduler;
import com.openhabbo.core.storage.ConnectionProvider;
import com.openhabbo.core.storage.Transaction;
import com.openhabbo.core.storage.dao.AbstractDao;
import com.openhabbo.core.storage.dao.DaoEventScheduler;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class CorePlayerInventoryDao extends AbstractDao implements PlayerInventoryDao {
    @Inject
    public CorePlayerInventoryDao(EventScheduler coreEventScheduler, DaoEventScheduler daoEventScheduler, ConnectionProvider connectionProvider) {
        super(coreEventScheduler, daoEventScheduler, connectionProvider);
    }

    @Override
    public void getFurnitureInventory(int playerId, Map<UUID, FurnitureInventoryItem> onComplete) {
//        Transaction transaction = this.createTransaction(Transaction.Type.SELECT, "SELECT * FROM items WHERE player_id = :playerId AND room_id = 0");
//
//        transaction.bindParam("playerId", playerId);
//
//        this.queueTransaction(transaction, (results) -> {
//            try {
//                while (results.hasResults()) {
//                    final String username = results.getString("username");
//                    final String figure = results.getString("figure");
//                    final PlayerGender gender = PlayerGender.getGender(results.getString("gender").toLowerCase());
//                    final String motto = results.getString("motto");
//                    final int rank = results.getInteger("rank");
//
//                    this.onComplete();
//                   // this.onComplete(results, onComplete, new PlayerDataBean(id, username, figure, gender, motto, rank));
//                }
//            } catch (Exception e) {
//                log.error("Error while submitting transaction for PlayerDao.getById", e);
//            }
//        });
    }
}
