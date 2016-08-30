package com.openhabbo.core.storage.data;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.util.Map;

public class ImmutableResultReader implements ResultReader {
    private static final Logger log = LogManager.getLogger(ImmutableResultReader.class);

    private final ResultSet resultSet;
    private final Map<String, Object> resultCache;

    public ImmutableResultReader(ResultSet resultSet) {
        this.resultSet = resultSet;
        this.resultCache = Maps.newHashMap();
    }

    @Override
    public Boolean hasResults() throws Exception {
        this.resultCache.clear();

        return !this.resultSet.isClosed() && this.resultSet.next();
    }

    @Override
    public String getString(String key) {
        if (this.resultCache.containsKey(key)) {
            return (String) this.resultCache.get(key);
        }

        try {
            final String result = this.resultSet.getString(key);

            this.resultCache.put(key, result);

            return result;
        } catch (Exception e) {
            log.error("Invalid result key {}", key, e);
        }

        return null;
    }

    @Override
    public Boolean getBoolean(String key) {
        if (this.resultCache.containsKey(key)) {
            return (Boolean) this.resultCache.get(key);
        }

        try {
            final boolean result = this.resultSet.getBoolean(key);

            this.resultCache.put(key, result);

            return result;
        } catch (Exception e) {
            log.error("Invalid result key {}", key, e);
        }

        return null;
    }

    @Override
    public Integer getInteger(String key) {
        if (this.resultCache.containsKey(key)) {
            return (Integer) this.resultCache.get(key);
        }

        try {
            final int result = this.resultSet.getInt(key);

            this.resultCache.put(key, result);

            return result;
        } catch (Exception e) {
            log.error("Invalid result key {}", key, e);
        }

        return null;
    }

    @Override
    public Long getLong(String key) {
        if (this.resultCache.containsKey(key)) {
            return (Long) this.resultCache.get(key);
        }

        try {
            final long result = this.resultSet.getLong(key);

            this.resultCache.put(key, result);

            return result;
        } catch (Exception e) {
            log.error("Invalid result key {}", key, e);
        }

        return null;
    }

    @Override
    public Double getDouble(String key) {
        if (this.resultCache.containsKey(key)) {
            return (Double) this.resultCache.get(key);
        }

        try {
            final double result = this.resultSet.getDouble(key);

            this.resultCache.put(key, result);

            return result;
        } catch (Exception e) {
            log.error("Invalid result key {}", key, e);
        }

        return null;
    }

    @Override
    public void close() throws Exception {
        this.resultSet.close();
        this.resultCache.clear();
    }
}
