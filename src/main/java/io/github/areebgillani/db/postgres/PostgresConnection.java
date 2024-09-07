package io.github.areebgillani.db.postgres;

import io.github.areebgillani.db.utils.AbstractConnection;
import io.github.areebgillani.db.utils.DatabaseConfig;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;

public class PostgresConnection extends AbstractConnection<PgConnectOptions, PgPool> {
    private static PostgresConnection instance;
    private DatabaseConfig config;
    public PostgresConnection(JsonObject config) {
        this.config = DatabaseConfig.getInstance(config);
        this.connectionOptions = getConnectionOption();
        this.poolOptions = getPoolOptions();
        this.client = getSQLPool();
    }

    protected PgPool getSQLPool() {
        return this.client == null ? PgPool.pool(vertx, connectionOptions, poolOptions) : this.client;
    }

    protected PoolOptions getPoolOptions() {
        return this.poolOptions == null ? new PoolOptions().setMaxSize(config.DB_POOL_SIZE) : poolOptions;
    }

    protected PgConnectOptions getConnectionOption() {
        return this.connectionOptions == null ? new PgConnectOptions()
                .setPort(config.DB_PORT)
                .setHost(config.DB_HOST)
                .setDatabase(config.DB_NAME)
                .setUser(config.DB_USERNAME)
                .setPassword(config.DB_PASSWORD)
                .setReconnectAttempts(config.DB_RETRY_COUNT)
                .setReconnectInterval(config.DB_RETRY_INTERVAL): this.connectionOptions;
    }
    public static PostgresConnection getInstance(JsonObject config) {
        return instance == null ? new PostgresConnection(config) : instance;
    }
}
