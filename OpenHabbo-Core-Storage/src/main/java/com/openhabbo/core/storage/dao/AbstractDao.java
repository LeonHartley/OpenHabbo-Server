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

public abstract class AbstractDao implements Runnable {
    private static final int PROCESS_EVENTS = 100;
    private static final int PROCESS_INTERVAL = 500;

    protected final Logger log = LogManager.getLogger(this.getClass().getName());

    private final ConnectionProvider connectionProvider;

    private final EventScheduler coreEventScheduler;

    private final BlockingQueue<DaoEvent> eventQueue;
    private final UUID eventId;

    private boolean shouldProcessQueue = true;

    public AbstractDao(EventScheduler coreEventScheduler, EventScheduler sqlEventScheduler, ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;

        if (!this.connectionProvider.isInitialised()) {
            this.connectionProvider.initialise();
        }

        this.coreEventScheduler = coreEventScheduler;

        // TODO: Fine tune the capacity.
        this.eventQueue = new ArrayBlockingQueue<>(10000);
        this.eventId = sqlEventScheduler.scheduleEvent(this, PROCESS_INTERVAL, PROCESS_EVENTS, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        final List<DaoEvent> events = new LinkedList<>();

        while (events.size() < PROCESS_EVENTS && !this.eventQueue.isEmpty()) {
            events.add(this.eventQueue.poll());
        }

        try (final Connection connection = this.connectionProvider.getConnection()) {
            for (DaoEvent event : events) {
                log.debug("Processing queued event");
                event.process(connection);
            }
        } catch (SQLException e) {
            log.error("Error while processing dao events");
        }

        events.clear();
    }

    public void onShutdown() {
        this.shouldProcessQueue = false;
    }

    protected Transaction createTransaction(Transaction.Type type, String query) {
        return new Transaction(type, query);
    }

    protected void queueTransaction(final Transaction transaction) {
        queueTransaction(transaction, null);
    }

    protected void queueTransaction(final Transaction transaction, Consumer<ResultReader> onComplete) {
        // create an event instance and queue it.
        // we need to figure out how we want to queue this event, if we're selecting, we wanna execute it right
        // away, but if it's a select or insert, we don't need to do that.
        try {
            if (transaction.getType() == Transaction.Type.SELECT) {
                this.processSelectTransaction(transaction, onComplete);
                return;
            }

            final DaoEvent daoEvent = new DaoEvent(transaction, onComplete);

            this.eventQueue.add(daoEvent);
        } catch (Exception e) {
            log.error("Error while queueing transaction", e);
        }
    }

    private void processSelectTransaction(final Transaction transaction, Consumer<ResultReader> onComplete) throws SQLException {
        final DaoEvent daoEvent = new DaoEvent(transaction, onComplete);

        try (final Connection connection = this.connectionProvider.getConnection()) {
            daoEvent.process(connection);
        }
    }

    public <T> void onComplete(ResultReader resultReader, Consumer<T> onComplete, T data) {
        this.coreEventScheduler.submit(() -> onComplete.accept(data));

        try {
            resultReader.close();
        } catch (Exception e) {
            log.error("Error while closing result reader object", e);
        }
    }
}
