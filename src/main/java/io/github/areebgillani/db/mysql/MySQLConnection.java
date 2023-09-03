package io.github.areebgillani.db.mysql;

import io.github.areebgillani.db.utils.AbstractConnection;
import io.github.areebgillani.db.utils.DatabaseConfig;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;

public class MySQLConnection extends AbstractConnection<MySQLConnectOptions, MySQLPool> {
    private static MySQLConnection instance;
    private DatabaseConfig config;
    public MySQLConnection(String connectionName) {
        this.config = DatabaseConfig.getInstance(Vertx.currentContext().config().getJsonObject("dbConnections").getJsonObject(connectionName));
        this.connectionOptions = getConnectionOption();
        this.poolOptions = getPoolOptions();
        this.client = getSQLPool();
    }
    public MySQLConnection(JsonObject config) {
        this.config = DatabaseConfig.getInstance(config);
        this.connectionOptions = getConnectionOption();
        this.poolOptions = getPoolOptions();
        this.client = getSQLPool();
    }

    protected MySQLPool getSQLPool() {
        return this.client == null ? MySQLPool.pool(vertx, connectionOptions, poolOptions) : this.client;
    }

    protected PoolOptions getPoolOptions() {
        return this.poolOptions == null ? new PoolOptions().setMaxSize(config.DB_POOL_SIZE) : poolOptions;
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
    public static MySQLConnection getInstance(String connectionName) {
        return instance == null ? new MySQLConnection(connectionName) : instance;
    }
    public static MySQLConnection getInstance(JsonObject config) {
        return instance == null ? new MySQLConnection(config) : instance;
    }
}
