package io.github.areebgillani.db.utils;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.templates.RowMapper;
import io.vertx.sqlclient.templates.TupleMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface Repository<T>{
    CompletableFuture<Boolean> save(T obj, String query, TupleMapper<T> mapper);
    CompletableFuture<Boolean> update(T obj, String query, TupleMapper<T> mapper);
    CompletableFuture<Boolean> delete(T obj, String query, TupleMapper<T> mapper);
    CompletableFuture<List<T>> read(String query, RowMapper<T> mapper, Map<String, Object> params);
    Future<?> saveOrUpdate(String query, HashMap<String,Object> hashMap);
    Future<List<Row>> readRows(String query, Map<String, Object> params);
    Future<List<JsonObject>> read(String query, Map<String, Object> params);

}
