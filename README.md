[![](https://jitpack.io/v/Areeb-Gillani/vertx-boost-db.svg)](https://jitpack.io/#Areeb-Gillani/vertx-boost-db)
# vertx-boost-db
This project (BoostDB) provides are a more easy to write code approach to write data-access layer. Keeping in mind the learing curve of vertx this project is designed to be as closer to SpringData layer as possible. This project provides are multi-tenant approach to configure multiple datasources using connection configuration.
# Background & Basics
### Vertx Vs Spring
Vertx is an event driven toolkit backed by eclipse foundation. It's a polyglot and is used for highly concurrent code writing. When compared to spring people usually use hibernate or spring data. They are heavy with their own pros and cons. For instance, in hibernate one can't bypass its L1 cache. Vertx on the other hand provides light-weight clients of most of the popular databases to get things going. This component is covering MySQL, MsSQL, Postgres, & Oracle database for now.
### Why Vertx?
When compared to Spring (Webflux or Boot) Vertx is exceptionally fast. In my performance testing I have found vertx 75% faster than spring. Techempower has also shared very similar results on their site as well https://www.techempower.com/benchmarks/#section=data-r21. Now considering this, if you want to develop a state of the art application with high throughput, one should go for vertx as it is Java's fastest available unopinionated framework available today (Techempower's results also backs this statement). 
### Basics of Vertx
Vertx out of the box provides SQL and NoSQL clients. Whereas those clients are event-driven, non-blocking and with low overhead. 
### Why BoostDB?
BoostDB helps you code in very similar way which you are used to in Spring. Just because the learning curve of vertx itself is a little tricky that's why it will also be a bit messy to arrange your code in best possible way. This project helps you in the best possible way to write performance efficient code without worrying about the bits and pieces of the low-end code.
# Dependency
 Add it in your root build.gradle at the end of repositories
### Repository
```kotlin
allprojects {
		repositories {
			maven ("https://jitpack.io")
		}
	}
```
### Adding Dependency

```kotlin
dependencies {
  implementation ("com.github.Areeb-Gillani:vertx-boost-db:0.0.1")
}
```
# Config
One can add multiple connection configurations in this way and upon creating the repository please make sure you pass the right connection name so that if the connection is not available in the application it will be created.
```json
{
  "dbConnections": {
    "Primary": {
      "dbName": "example_database",
      "dbHost":"localhost",
      "dbPort": 3306,
      "dbUsername": "root",
      "dbPassword": "password",
      "dbConnectionPoolSize": 10,
      "dbType": "MYSQL",
      "dbRetryCount": 5,
      "dbRetryInterval": 1000
    },
    "MySecondConnection": {
      "dbName": "example_database",
      "dbHost":"127.0.0.1",
      "dbPort": 1433,
      "dbUsername": "root",
      "dbPassword": "password",
      "dbConnectionPoolSize": 10,
      "dbType": "MSSQL",
      "dbRetryCount": 5,
      "dbRetryInterval": 1000
    }
  }
}
```

# Usage
This project out of the box gives CrudRepository, MySQLRepository, MSSQLRepository, PostgresRepository, and OracleRepository which a user can extend. It is prefered to use CrudRepository as its factory can create all type of repos based on the dbType attribute present in the connection configuration. 

```java
public class MyRepo extends CrudRepository<ExampleModel> {
    public MyRepo(String connectionName){
        super(connectionName);
    }
    //Please code other related stuff here
}
```
#### Note: Please include the specific client driver in your build.gradle in order to make this code work
```kotlin
    dependencies{
          implementation ("io.vertx:vertx-mysql-client:4.4.5")
    }
```



