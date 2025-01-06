package io.github.areebgillani.db.utils;


import io.vertx.sqlclient.PoolOptions;

public interface DBConnection<T, C> {

    C getDBPool();

    PoolOptions getPoolOptions(int poolSize);
}
