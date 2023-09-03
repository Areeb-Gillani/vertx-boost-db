package io.github.areebgillani.db.mysql;


import io.github.areebgillani.db.utils.AbstractRepository;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class MySQLRepository<T> extends AbstractRepository<T> {
    public MySQLRepository(Vertx vertx, JsonObject config){
        databaseConnection = MySQLConnection.getInstance(vertx, config);
    }
}
