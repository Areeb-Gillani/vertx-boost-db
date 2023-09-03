package io.github.areebgillani.db.postgres;


import io.github.areebgillani.db.utils.AbstractRepository;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class PostgresRepository<T> extends AbstractRepository<T> {

    public PostgresRepository(Vertx vertx, JsonObject config) {
        databaseConnection = new PostgresConnection(vertx, config);
    }
}
