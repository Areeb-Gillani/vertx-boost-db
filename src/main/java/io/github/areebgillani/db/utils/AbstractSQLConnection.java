package io.github.areebgillani.db.utils;

import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlConnectOptions;

public abstract class AbstractSQLConnection <T extends SqlConnectOptions> extends AbstractConnection <T, Pool>{
    protected DatabaseConfig config;
    public AbstractSQLConnection(JsonObject config) {
        this.config = DatabaseConfig.getInstance(config);
        this.poolOptions = getPoolOptions(this.config.DB_POOL_SIZE);
        this.client = getSQLPool();
    }

    @Override
    protected Pool getSQLPool() {
        return this.client == null ? Pool.pool(vertx, connectionOptions, poolOptions) : this.client;
    }
    @Override
    protected PoolOptions getPoolOptions(int poolSize) {
        return this.poolOptions == null ? new PoolOptions().setMaxSize(poolSize) : poolOptions;
    }

    protected abstract T getConnectionOption();
}
