package io.github.areebgillani.db.boost;

import io.github.areebgillani.db.mssql.MSSQLConnection;
import io.github.areebgillani.db.mysql.MySQLConnection;
import io.github.areebgillani.db.oracle.OracleConnection;
import io.github.areebgillani.db.postgres.PostgresConnection;
import io.github.areebgillani.db.utils.AbstractConnection;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;

public class ConnectionManager<T> {
    private static final HashMap<String, Object> dbConnectionMap = new HashMap<>();

    private static <T extends AbstractConnection> Object createDatabaseConnection(String connectionName, JsonObject config) {
        JsonObject conf = config.getJsonObject("dbConnections").getJsonObject(connectionName);
        return switch (DatabaseType.valueOf(conf.getString("dbType"))) {
            case POSTGRESQL -> PostgresConnection.getInstance(conf);
            case MYSQL -> MySQLConnection.getInstance(conf);
            case ORACLE -> OracleConnection.getInstance(conf);
            case MSSQL -> MSSQLConnection.getInstance(conf);
        };
    }

    public static <T extends AbstractConnection> T getDBConnection(String connectionName, JsonObject config) {
        Object con = dbConnectionMap.get(connectionName);
        if (con == null)
            con = dbConnectionMap.put(connectionName, createDatabaseConnection(connectionName, config));
        return (T) con;
    }
}
