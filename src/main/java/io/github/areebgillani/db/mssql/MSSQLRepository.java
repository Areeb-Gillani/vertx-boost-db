package io.github.areebgillani.db.mssql;

import io.github.areebgillani.db.utils.AbstractRepository;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class MSSQLRepository<T> extends AbstractRepository<T> {
    public MSSQLRepository(String connectionName, JsonObject config) {
        databaseConnection = MSSQLConnection.getInstance(config.getJsonObject("dbConnections").getJsonObject(connectionName));
    }
}
