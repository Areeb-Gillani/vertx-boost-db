package io.github.areebgillani.db.mysql;


import io.github.areebgillani.db.utils.AbstractRepository;
import io.vertx.core.json.JsonObject;

public class MySQLRepository<T> extends AbstractRepository<T> {
    public MySQLRepository(String connectionName, JsonObject config){
        databaseConnection = MySQLConnection.getInstance(config.getJsonObject("dbConnections").getJsonObject(connectionName));
    }
}
