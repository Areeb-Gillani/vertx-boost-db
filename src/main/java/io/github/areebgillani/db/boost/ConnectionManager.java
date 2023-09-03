package io.github.areebgillani.db.boost;

import io.github.areebgillani.db.mysql.MySQLConnection;
import io.github.areebgillani.db.oracle.OracleConnection;
import io.github.areebgillani.db.postgres.PostgresConnection;
import io.github.areebgillani.db.utils.AbstractConnection;
import io.github.areebgillani.db.mssql.MSSQLConnection;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;

public class ConnectionManager<T> {
    private static final HashMap<String, Object> dbConnectionMap = new HashMap<>();

     private static <T extends AbstractConnection> Object createDatabaseConnection(Vertx vertx, JsonObject config){
         return switch (DatabaseType.valueOf(config.getString("dbType"))){
            case POSTGRESQL -> PostgresConnection.getInstance(vertx, config);
            case MYSQL -> MySQLConnection.getInstance(vertx, config);
            case ORACLE -> OracleConnection.getInstance(vertx, config);
            case MSSQL -> MSSQLConnection.getInstance(vertx, config);
        };
    }
    public static <T extends AbstractConnection> T getDBConnection(Vertx vertx, String connectionName, JsonObject config){
        return (T) dbConnectionMap.computeIfAbsent(connectionName, type->createDatabaseConnection(vertx, config));
    }
}
