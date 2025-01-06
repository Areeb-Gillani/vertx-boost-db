package io.github.areebgillani.db.boost;

import io.github.areebgillani.db.mssql.MSSQLConnection;
import io.github.areebgillani.db.mysql.MySQLConnection;
import io.github.areebgillani.db.oracle.OracleConnection;
import io.github.areebgillani.db.postgres.PostgresConnection;
import io.github.areebgillani.db.utils.AbstractSQLConnection;
import io.github.areebgillani.db.utils.DBConnection;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.SqlConnectOptions;

import java.util.HashMap;

public class ConnectionManager<T> {
    private static final HashMap<String, Object> dbConnectionMap = new HashMap<>();

    private static <T extends AbstractSQLConnection> Object createDatabaseConnection(String connectionName, JsonObject config) {
        JsonObject conf = config.getJsonObject("dbConnections").getJsonObject(connectionName);
        return switch (DatabaseType.valueOf(conf.getString("dbType"))) {
            case POSTGRESQL -> PostgresConnection.getInstance(conf);
            case MYSQL -> MySQLConnection.getInstance(conf);
            case ORACLE -> OracleConnection.getInstance(conf);
            case MSSQL -> MSSQLConnection.getInstance(conf);
        };
    }

    public static <T extends AbstractSQLConnection> T getDBConnection(String connectionName, JsonObject config) {
        return (T) dbConnectionMap.computeIfAbsent(connectionName, key->createDatabaseConnection(key, config));
    }
}
