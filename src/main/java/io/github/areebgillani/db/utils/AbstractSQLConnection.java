package io.github.areebgillani.db.utils;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlConnectOptions;

public abstract class AbstractSQLConnection<T extends SqlConnectOptions> implements DBConnection<T, Pool> {
    protected DatabaseConfig config;
    protected Vertx vertx;
    public Pool client;
    protected T connectionOptions;
    PoolOptions poolOptions;
    public AbstractSQLConnection(){
        this.vertx = Vertx.currentContext().owner();
    }
    public AbstractSQLConnection(JsonObject config) {
        this.config = DatabaseConfig.getInstance(config);
        this.poolOptions = getPoolOptions(this.config.DB_POOL_SIZE);
        this.client = getDBPool();
    }

    @Override
    public Pool getDBPool() {
        return this.client == null ? Pool.pool(vertx, getConnectionOption(), poolOptions) : this.client;
    }
    @Override
    public PoolOptions getPoolOptions(int poolSize) {
        return this.poolOptions == null ? new PoolOptions().setMaxSize(poolSize) : poolOptions;
    }

    protected abstract T getConnectionOption();
}
