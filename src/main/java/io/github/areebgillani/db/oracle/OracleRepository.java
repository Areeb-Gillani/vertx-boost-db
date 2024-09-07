package io.github.areebgillani.db.oracle;


import io.github.areebgillani.db.utils.AbstractRepository;
import io.vertx.core.json.JsonObject;

public class OracleRepository<T> extends AbstractRepository<T> {
    public OracleRepository(String connectionName, JsonObject config) {
        databaseConnection = OracleConnection.getInstance(config.getJsonObject("dbConnections").getJsonObject(connectionName));
    }
}
