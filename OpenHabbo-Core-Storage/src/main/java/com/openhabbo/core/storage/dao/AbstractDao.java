package com.openhabbo.core.storage.dao;

import com.openhabbo.api.threading.EventScheduler;
import com.openhabbo.core.storage.ConnectionProvider;
import com.openhabbo.core.storage.Transaction;
import com.openhabbo.core.storage.data.ResultReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public abstract class AbstractDao {
    protected final Logger log = LogManager.getLogger(this.getClass().getName());

    private final ConnectionProvider connectionProvider;

    private final EventScheduler coreEventScheduler;
    private final DaoEventScheduler daoEventScheduler;

    public AbstractDao(EventScheduler coreEventScheduler, DaoEventScheduler daoEventScheduler, ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;

        if (!this.connectionProvider.isInitialised()) {
            this.connectionProvider.initialise();
        }

        this.coreEventScheduler = coreEventScheduler;
        this.daoEventScheduler = daoEventScheduler;
    }

    protected Transaction createTransaction(Transaction.Type type, String query) {
        return new Transaction(type, query);
    }

    protected void queueTransaction(final Transaction transaction) {
        queueTransaction(transaction, null);
    }

    protected void queueTransaction(final Transaction transaction, Consumer<ResultReader> onComplete) {
        try {
            this.daoEventScheduler.submit(() -> {
                try {
                    this.processTransaction(transaction, onComplete);
                } catch (Exception e) {
                    log.error("Error while handing queued transaction", e);
                }
            });
        } catch (Exception e) {
            log.error("Error while queueing transaction", e);
        }
    }

    private void processTransaction(final Transaction transaction, Consumer<ResultReader> onComplete) throws SQLException {
        final DaoEvent daoEvent = new DaoEvent(transaction, onComplete);

        try (final Connection connection = this.connectionProvider.getConnection()) {
            daoEvent.process(connection);
        }
    }

    public <T> void onComplete(ResultReader resultReader, Consumer<T> onComplete, T data) {
        // switch to the core event thread pool, we don't wanna be running the onComplete callbacks on DAO threads.
        this.coreEventScheduler.submit(() -> onComplete.accept(data));

        try {
            resultReader.close();
        } catch (Exception e) {
            log.error("Error while closing result reader object", e);
        }
    }
}
