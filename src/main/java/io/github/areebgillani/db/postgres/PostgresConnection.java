package io.github.areebgillani.db.postgres;

import io.github.areebgillani.db.utils.AbstractConnection;
import io.github.areebgillani.db.utils.AbstractSQLConnection;
import io.github.areebgillani.db.utils.DatabaseConfig;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;

public class PostgresConnection extends AbstractSQLConnection<PgConnectOptions> {
    private static PostgresConnection instance;
    private DatabaseConfig config;
    public PostgresConnection(JsonObject config) {
        super(config);
        this.connectionOptions = getConnectionOption();
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
