package io.github.areebgillani.db.oracle;

import io.github.areebgillani.db.utils.AbstractSQLConnection;
import io.github.areebgillani.db.utils.DatabaseConfig;
import io.vertx.core.json.JsonObject;
import io.vertx.oracleclient.OracleConnectOptions;

public class OracleConnection extends AbstractSQLConnection<OracleConnectOptions> {
    private static OracleConnection instance;
    private DatabaseConfig config;
    public OracleConnection(JsonObject config) {
        super(config);
        this.connectionOptions = getConnectionOption();
    }

    protected OracleConnectOptions getConnectionOption() {
        return this.connectionOptions == null ? new OracleConnectOptions()
                .setPort(config.DB_PORT)
                .setHost(config.DB_HOST)
                .setDatabase(config.DB_NAME)
                .setUser(config.DB_USERNAME)
                .setPassword(config.DB_PASSWORD) : this.connectionOptions;
    }
    public static OracleConnection getInstance(JsonObject config) {
        return instance == null ? new OracleConnection(config) : instance;
    }
}
