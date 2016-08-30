package com.openhabbo.core.storage.dao;

import com.openhabbo.core.storage.Transaction;
import com.openhabbo.core.storage.data.ResultReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.function.Consumer;

public class DaoEvent {
    private final Logger log = LogManager.getLogger(DaoEvent.class);

    private final Transaction transaction;
    private final Consumer<ResultReader> callback;

    public DaoEvent(final Transaction transaction, final Consumer<ResultReader> callback) {
        this.transaction = transaction;
        this.callback = callback;
    }

    public void process(final Connection connection) {
        try {
            if(this.transaction.getType() == Transaction.Type.SELECT) {
                final ResultReader results = this.transaction.getResults(connection);

                if (results != null) {
                    callback.accept(results);
                }
            } else {
                this.transaction.commit(connection);
            }
        } catch (Exception e) {
            log.error("Error while processing DaoEvent", e);
        }
    }
}
