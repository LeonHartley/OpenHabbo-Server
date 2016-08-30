package com.openhabbo.api.storage.dao;

import java.util.function.Consumer;

public interface Dao<T, I> {

    void getById(I id, Consumer<T> onComplete);

    void save(T instance);

    void deleteById(I id);
}
