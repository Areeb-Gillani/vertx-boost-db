package io.github.areebgillani.db.postgres;


import io.github.areebgillani.db.utils.AbstractRepository;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class PostgresRepository<T> extends AbstractRepository<T> {

    public PostgresRepository(String connectionName, JsonObject config) {
        databaseConnection = PostgresConnection.getInstance(config.getJsonObject("dbConnections").getJsonObject(connectionName));
    }
}
