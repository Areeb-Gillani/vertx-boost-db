package io.github.areebgillani.db.utils;

import io.github.areebgillani.db.boost.DatabaseType;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
public class DatabaseConfig {
    private static DatabaseConfig instance;
    public  int DB_PORT;
    public  int DB_POOL_SIZE;
    public  String DB_HOST;
    public  String DB_USERNAME;
    public  String DB_PASSWORD;
    public  String DB_NAME;
    public  DatabaseType DB_TYPE;
    public  int DB_RETRY_COUNT;
    public  int DB_RETRY_INTERVAL;
    public static DatabaseConfig getInstance(JsonObject config){
        if(instance==null) {
            instance = new DatabaseConfig(config);
        }
        return instance;
    }
    private DatabaseConfig(JsonObject config){
        setDatabaseConnection(config);
    }
    
    private void setDatabaseConnection(JsonObject config) {
        DB_PORT = config.getInteger("dbPort");
        DB_HOST = config.getString("dbHost");
        DB_NAME = config.getString("dbName");
        DB_USERNAME = config.getString("dbUsername");
        DB_PASSWORD = config.getString("dbPassword");
        DB_POOL_SIZE = config.getInteger("dbConnectionPoolSize");
        DB_TYPE = DatabaseType.valueOf(config.getString("dbType"));
        DB_RETRY_COUNT = config.getInteger("dbRetryCount");
        DB_RETRY_INTERVAL = config.getInteger("dbRetryInterval");
    }

}
