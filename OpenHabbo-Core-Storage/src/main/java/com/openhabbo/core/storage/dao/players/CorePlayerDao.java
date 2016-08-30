package com.openhabbo.core.storage.dao.players;

import com.google.inject.Inject;
import com.openhabbo.api.game.players.PlayerData;
import com.openhabbo.api.game.players.data.PlayerGender;
import com.openhabbo.api.storage.dao.players.PlayerDao;
import com.openhabbo.api.threading.EventScheduler;
import com.openhabbo.core.storage.ConnectionProvider;
import com.openhabbo.core.storage.Transaction;
import com.openhabbo.core.storage.dao.AbstractDao;
import com.openhabbo.core.storage.dao.DaoEventScheduler;
import com.openhabbo.core.storage.dao.players.beans.PlayerDataBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public class CorePlayerDao extends AbstractDao implements PlayerDao {
    private static final Logger log = LogManager.getLogger(CorePlayerDao.class);

    @Inject
    public CorePlayerDao(EventScheduler eventScheduler, DaoEventScheduler daoEventScheduler, ConnectionProvider connectionProvider) {
        super(eventScheduler, daoEventScheduler, connectionProvider);
    }

    @Override
    public void getById(Integer id, Consumer<PlayerData> onComplete) {
        Transaction transaction = this.createTransaction(Transaction.Type.SELECT, "SELECT `username`, `figure`, `gender`, `motto`, `rank` " +
                "FROM players WHERE id = :playerId");

        transaction.bindParam("playerId", id);

        this.queueTransaction(transaction, (results) -> {
            try {
                while (results.hasResults()) {
                    final String username = results.getString("username");
                    final String figure = results.getString("figure");
                    final PlayerGender gender = PlayerGender.getGender(results.getString("gender").toLowerCase());
                    final String motto = results.getString("motto");
                    final int rank = results.getInteger("rank");

                    this.onComplete(results, onComplete, new PlayerDataBean(id, username, figure, gender, motto, rank));
                }
            } catch (Exception e) {
                log.error("Error while submitting transaction for PlayerDao.getById", e);
            }
        });
    }

    @Override
    public void save(PlayerData instance) {
        Transaction transaction = this.createTransaction(Transaction.Type.UPDATE, "UPDATE players SET username = :username, " +
                "figure = :figure, motto = :motto, gender = :gender WHERE id = :playerId");

        transaction.bindParam("playerId", instance.getId());
        transaction.bindParam("username", instance.getUsername());
        transaction.bindParam("figure", instance.getFigure());
        transaction.bindParam("motto", instance.getMotto());
        transaction.bindParam("gender", instance.getGender().toString());

        this.queueTransaction(transaction);
    }

    @Override
    public void deleteById(Integer id) {
        log.warn("Player objects cannot be deleted");
    }

    @Override
    public void getDataByTicket(String ssoTicket, Consumer<PlayerData> consumer) {
        Transaction transaction = this.createTransaction(Transaction.Type.SELECT,
                "SELECT `id`, `username`, `figure`, `gender`, `motto`, `rank` FROM players WHERE auth_ticket = :ssoTicket");

        transaction.bindParam("ssoTicket", ssoTicket);

        this.queueTransaction(transaction, (results) -> {
            try {
                while (results.hasResults()) {
                    final int playerId = results.getInteger("id");
                    final String username = results.getString("username");
                    final String figure = results.getString("figure");
                    final PlayerGender gender = PlayerGender.getGender(results.getString("gender").toLowerCase());
                    final String motto = results.getString("motto");
                    final int rank = results.getInteger("rank");

                    this.onComplete(results, consumer,
                            new PlayerDataBean(playerId, username, figure, gender, motto, rank));
                }
            } catch (Exception e) {
                log.error("Error while submitting transaction for PlayerDao.getById", e);
            }
        });
    }
}
