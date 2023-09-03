package io.github.areebgillani.db.oracle;


import io.github.areebgillani.db.utils.AbstractRepository;
import io.vertx.core.Vertx;

public class OracleRepository<T> extends AbstractRepository<T> {
    public OracleRepository(Vertx vertx) {
        databaseConnection = new OracleConnection(vertx);
    }
}
