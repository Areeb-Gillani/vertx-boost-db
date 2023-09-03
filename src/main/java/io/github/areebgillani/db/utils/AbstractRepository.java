package io.github.areebgillani.db.utils;

import io.vertx.sqlclient.SqlClient;
import io.vertx.sqlclient.templates.RowMapper;
import io.vertx.sqlclient.templates.SqlTemplate;
import io.vertx.sqlclient.templates.TupleMapper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AbstractRepository<T>{
    public AbstractConnection databaseConnection;
    public CompletableFuture<Boolean> save(T obj, String query, TupleMapper<T> mapper) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        SqlTemplate
                .forUpdate((SqlClient) databaseConnection.client, query)
                .mapFrom(mapper)
                .execute(obj)
                .onSuccess(result -> future.complete(true))
                .onFailure(res -> future.complete(false));
        return future;
    }
    public CompletableFuture<Boolean> update(T obj, String query, TupleMapper<T> mapper) {
        return save(obj, query, mapper);
    }
    public CompletableFuture<Boolean> delete(T obj, String query, TupleMapper<T> mapper) {
        return save(obj, query, mapper);
    }
    public CompletableFuture<List<T>> read(String query, RowMapper<T> mapper, HashMap<String, Object> params) {
        CompletableFuture<List<T>> future = new CompletableFuture<>();
        List<T> list = new LinkedList<>();
        SqlTemplate
                .forQuery((SqlClient) databaseConnection.client, query)
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
}
