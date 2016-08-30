package com.openhabbo.core.storage;

import com.google.common.collect.Maps;
import com.openhabbo.core.storage.data.ImmutableResultReader;
import com.openhabbo.core.storage.data.ResultReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class Transaction implements AutoCloseable {
    private final Type type;
    private String statement;

    private PreparedStatement preparedStatement;
    private ResultReader results;

    private Map<String, Object> parameters;

    public Transaction(Type type, String statement) {
        this.type = type;
        this.statement = statement;

        this.parameters = Maps.newHashMap();
    }

    public void bindParam(String name, Object param) {
        this.parameters.put(name, param);
    }

    public ResultReader getResults(Connection connection) throws SQLException {
        if (this.results == null) {
            this.processStatement(connection);

            this.results = new ImmutableResultReader(this.preparedStatement.executeQuery());
        }

        return this.results;
    }

    private void processStatement(Connection connection) throws SQLException {
        int indexCounter = 1;

        for (Map.Entry<String, Object> parameter : this.parameters.entrySet()) {
            this.statement = this.statement.replace(":" + parameter.getKey(), "?");
        }

        this.preparedStatement = connection.prepareStatement(this.statement);

        for (Map.Entry<String, Object> parameter : this.parameters.entrySet()) {
            this.preparedStatement.setObject(indexCounter, parameter.getValue());

            indexCounter++;
        }
    }

    public void commit(Connection connection) throws SQLException {
        this.processStatement(connection);

        this.preparedStatement.execute();
    }

    @Override
    public void close() throws Exception {
        if (this.preparedStatement != null) {
            this.preparedStatement.close();
        }

        if (this.results != null) {
            this.results.close();
        }

        if (this.parameters != null) {
            this.parameters.clear();
        }
    }

    public Type getType() {
        return this.type;
    }

    public enum Type {
        UPDATE,
        SELECT,
        INSERT
    }
}
