package io.github.areebgillani.db.mysql;

import io.github.areebgillani.db.utils.AbstractConnection;
import io.github.areebgillani.db.utils.AbstractSQLConnection;
import io.github.areebgillani.db.utils.DatabaseConfig;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;

public class MySQLConnection extends AbstractSQLConnection<MySQLConnectOptions> {
    private static MySQLConnection instance;
    public MySQLConnection(JsonObject config) {
        super(config);
        this.connectionOptions = getConnectionOption();
    }

    protected MySQLConnectOptions getConnectionOption() {
        return this.connectionOptions == null ? new MySQLConnectOptions()
                .setPort(config.DB_PORT)
                .setHost(config.DB_HOST)
                .setDatabase(config.DB_NAME)
                .setUser(config.DB_USERNAME)
                .setPassword(config.DB_PASSWORD)
                .setReconnectAttempts(config.DB_RETRY_COUNT)
                .setReconnectInterval(config.DB_RETRY_INTERVAL): this.connectionOptions;
    }
    public static MySQLConnection getInstance(JsonObject config) {
        return instance == null ? new MySQLConnection(config) : instance;
    }
}
