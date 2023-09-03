package io.github.areebgillani.db.boost;


import io.github.areebgillani.db.utils.AbstractRepository;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class CrudRepository<T> extends AbstractRepository<T> {
    public CrudRepository(String connectionName){
        databaseConnection = ConnectionManager.getDBConnection(connectionName);
    }
}
