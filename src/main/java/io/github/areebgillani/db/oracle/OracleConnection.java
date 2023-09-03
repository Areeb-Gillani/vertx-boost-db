package io.github.areebgillani.db.oracle;

import io.github.areebgillani.db.mssql.MSSQLConnection;
import io.github.areebgillani.db.utils.AbstractConnection;
import io.github.areebgillani.db.utils.DatabaseConfig;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.oracleclient.OracleConnectOptions;
import io.vertx.oracleclient.OraclePool;
import io.vertx.sqlclient.PoolOptions;

public class OracleConnection extends AbstractConnection<OracleConnectOptions, OraclePool> {
    private static OracleConnection instance;
    private DatabaseConfig config;
    public OracleConnection(String connectionName) {
        this.config = DatabaseConfig.getInstance(Vertx.currentContext().config().getJsonObject("dbConnections").getJsonObject(connectionName));
        this.connectionOptions = getConnectionOption();
        this.poolOptions = getPoolOptions();
        this.client = getSQLPool();
    }
    public OracleConnection(JsonObject config) {
        this.config = DatabaseConfig.getInstance(config);
        this.connectionOptions = getConnectionOption();
        this.poolOptions = getPoolOptions();
        this.client = getSQLPool();
    }

    protected OraclePool getSQLPool() {
        return this.client == null ? OraclePool.pool(vertx, connectionOptions, poolOptions) : this.client;
    }

    protected PoolOptions getPoolOptions() {
        return this.poolOptions == null ? new PoolOptions().setMaxSize(config.DB_POOL_SIZE) : poolOptions;
    }

    protected OracleConnectOptions getConnectionOption() {
        return this.connectionOptions == null ? new OracleConnectOptions()
                .setPort(config.DB_PORT)
                .setHost(config.DB_HOST)
                .setDatabase(config.DB_NAME)
                .setUser(config.DB_USERNAME)
                .setPassword(config.DB_PASSWORD) : this.connectionOptions;
    }


    public static OracleConnection getInstance(String connectionName) {
        return instance == null ? new OracleConnection(connectionName) : instance;
    }
    public static OracleConnection getInstance(JsonObject config) {
        return instance == null ? new OracleConnection(config) : instance;
    }
}
