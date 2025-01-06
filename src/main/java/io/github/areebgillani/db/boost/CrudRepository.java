package io.github.areebgillani.db.boost;


import io.github.areebgillani.db.utils.AbstractSQLConnection;
import io.github.areebgillani.db.utils.Repository;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.SqlClient;
import io.vertx.sqlclient.templates.RowMapper;
import io.vertx.sqlclient.templates.SqlTemplate;
import io.vertx.sqlclient.templates.TupleMapper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class CrudRepository<T> implements Repository<T> {

    public AbstractSQLConnection databaseConnection;
    private final Logger logger = LoggerFactory.getLogger(CrudRepository.class);
    public CrudRepository(String connectionName, JsonObject config){
        databaseConnection = ConnectionManager.getDBConnection(connectionName, config);
    }
    @Override
    public CompletableFuture<Boolean> save(T obj, String query, TupleMapper<T> mapper) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        SqlTemplate
                .forUpdate(databaseConnection.client, query)
                .mapFrom(mapper)
                .execute(obj)
                .onSuccess(result -> future.complete(true))
                .onFailure(res -> future.complete(false));
        return future;
    }
    @Override
    public CompletableFuture<Boolean> update(T obj, String query, TupleMapper<T> mapper) {
        return save(obj, query, mapper);
    }
    @Override
    public CompletableFuture<Boolean> delete(T obj, String query, TupleMapper<T> mapper) {
        return save(obj, query, mapper);
    }
    @Override
    public CompletableFuture<List<T>> read(String query, RowMapper<T> mapper, Map<String, Object> params) {
        CompletableFuture<List<T>> future = new CompletableFuture<>();
        List<T> list = new LinkedList<>();
        SqlTemplate.forQuery(databaseConnection.client, query)
                .mapTo(mapper)
                .execute(params)
                .onSuccess(result -> {
                    for (T t : result) {
                        list.add(t);
                    }
                    future.complete(list);
                })
                .onFailure(res -> list.clear());
        return future;
    }
    @Override
    public Future<?> saveOrUpdate(String query, HashMap<String,Object> hashMap) {
        Promise<Boolean> promise = Promise.promise();
        SqlTemplate
                .forUpdate(databaseConnection.client, query)
                .execute(hashMap)
                .onSuccess(result -> promise.complete(true))
                .onFailure(res -> {
                    logger.error(res.getMessage(), res);
                    promise.complete(false);
                });
        return promise.future();
    }
    @Override
    public Future<List<Row>> readRows(String query, Map<String, Object> params) {
        Promise<List<Row>> promise = Promise.promise();
        List<Row> list = new LinkedList<>();
        SqlTemplate
                .forQuery(databaseConnection.client, query)
                .execute(params)
                .onSuccess(result -> {
                    for (Row t : result) {
                        list.add(t);
                    }
                    promise.complete(list);
                })
                .onFailure(res -> {
                    list.clear();
                    promise.fail(res);
                });
        return promise.future();
    }
    @Override
    public Future<List<JsonObject>> read(String query, Map<String, Object> params) {
        Promise<List<JsonObject>> promise = Promise.promise();
        List<JsonObject> list = new LinkedList<>();
        SqlTemplate
                .forQuery(databaseConnection.client, query)
                .execute(params)
                .onSuccess(result -> {
                    for (Row t : result) {
                        list.add(t.toJson());
                    }
                    promise.complete(list);
                })
                .onFailure(res -> {
                    list.clear();
                    promise.fail(res);
                });
        return promise.future();
    }
}
