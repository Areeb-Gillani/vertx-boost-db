package io.github.areebgillani.db.utils;


import io.vertx.core.Vertx;
import io.vertx.sqlclient.PoolOptions;

public abstract class AbstractConnection<T, C> {
    protected C client = null;
    protected T connectionOptions = null;
    protected PoolOptions poolOptions = null;

    protected Vertx vertx;
    public AbstractConnection(Vertx vertx){
        this.vertx = vertx;
    }

    protected abstract C getSQLPool();

    protected abstract PoolOptions getPoolOptions();

    protected abstract T getConnectionOption();
}
