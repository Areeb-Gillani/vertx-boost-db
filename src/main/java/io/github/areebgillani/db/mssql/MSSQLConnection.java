package io.github.areebgillani.db.mssql;

import io.github.areebgillani.db.utils.AbstractConnection;
import io.github.areebgillani.db.utils.AbstractSQLConnection;
import io.github.areebgillani.db.utils.DatabaseConfig;
import io.vertx.core.json.JsonObject;
import io.vertx.mssqlclient.MSSQLConnectOptions;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;

public class MSSQLConnection extends AbstractSQLConnection<MSSQLConnectOptions> {
    private static MSSQLConnection instance;
    private DatabaseConfig config;
    public MSSQLConnection(JsonObject config) {
        super(config);
        this.connectionOptions = getConnectionOption();
    }
    protected MSSQLConnectOptions getConnectionOption() {
        return this.connectionOptions == null ? new MSSQLConnectOptions()
                .setPort(config.DB_PORT)
                .setHost(config.DB_HOST)
                .setDatabase(config.DB_NAME)
                .setUser(config.DB_USERNAME)
                .setPassword(config.DB_PASSWORD)
                .setReconnectAttempts(config.DB_RETRY_COUNT)
                .setReconnectInterval(config.DB_RETRY_INTERVAL): this.connectionOptions;
    }
    public static MSSQLConnection getInstance(JsonObject config) {
        return instance == null ? new MSSQLConnection(config) : instance;
    }
}
