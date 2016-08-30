package com.openhabbo.core.storage;

import com.google.inject.Singleton;
import com.netflix.governator.annotations.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

@Singleton
public class ConnectionProvider {
    private final Logger log = LogManager.getLogger(ConnectionProvider.class);

    @Configuration("openhabbo.storage.mysql.jdbcurl")
    private String jdbcUrl;

    @Configuration("openhabbo.storage.mysql.username")
    private String username;

    @Configuration("openhabbo.storage.mysql.password")
    private String password;

    @Configuration("openhabbo.storage.mysql.poolMaximum")
    private int poolMaximum = 50;

    @Configuration("openhabbo.storage.mysql.slowTransactionLog")
    private boolean slowTransactionLog = false;

    @Configuration("openhabbo.storage.mysql.slowTransactionThreshold")
    private int slowTransactionThreshold = 100;

    private HikariDataSource hikariDataSource;
    private boolean initialised = false;

    public ConnectionProvider() {

    }

    public void initialise() {
        boolean isConnectionFailed = false;

        try {
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(this.jdbcUrl);

            config.setUsername(this.username);
            config.setPassword(this.password);

            config.setMaximumPoolSize(this.poolMaximum);

            this.hikariDataSource = new HikariDataSource(config);
            this.initialised = true;
        } catch (Exception e) {
            log.error("Failed to initialise data source", e);
            isConnectionFailed = true;
            System.exit(0);
        } finally {
            if (!isConnectionFailed) {
                log.info("MySQL datasource initialised successfully");
            }
        }
    }

    public Connection getConnection() throws SQLException {
        return this.hikariDataSource.getConnection();
    }

    public boolean isInitialised() {
        return initialised;
    }
}
