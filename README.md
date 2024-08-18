[![](https://jitpack.io/v/Areeb-Gillani/vertx-boost-db.svg)](https://jitpack.io/#Areeb-Gillani/vertx-boost-db)
# vertx-boost-db
This project (BoostDB) provides easier ways to write code for a data-access layer. Keeping in mind the learning curve of vertx, this project is designed to be as close to the SpringData layer as possible. This project provides a multi-tenant approach to configuring multiple data sources using connection configuration.
# Background & Basics
### Vertx vs. Spring
Vertx is an event-driven toolkit backed by the Eclipse Foundation. It's a polyglot and is used for highly concurrent code writing. When compared to spring, people usually use hibernate or spring data. They are heavy with their own pros and cons. For instance, in hibernate, one can't bypass its L1 cache. Vertx on the other hand provides lightweight clients for most of the popular databases to get things going. This component is covering MySQL, MSSQL, Postgres, and Oracle databases for now.
### Why Vertx?
When compared to Spring (Webflux or Boot), Vertx is exceptionally fast. In my performance testing, I found Vertex to be 75% faster than Spring. Techempower has also shared very similar results on their site: https://www.techempower.com/benchmarks/#section=data-r21. Now considering this, if you want to develop a state-of-the art application with high throughput, one should go for vertx, as it is Java's fastest unopinionated framework available today (Techempower's results also back this statement).
### Basics of Vertx
Vertx, out of the box, provides SQL and NoSQL clients. Whereas those clients are event-driven, non-blocking, and have low overhead.
### Why BoostDB?
BoostDB helps you code in a very similar way to what you are used to in Spring. Just because the learning curve of vertx itself is a little tricky, it will also be a bit messy to arrange your code in the best possible way. This project helps you in the best possible way to write performance-efficient code without worrying about the bits and pieces of low-end code.
# Dependency
### Repository
#### build.gradle
```kotlin
allprojects {
        repositories {
            maven ("https://jitpack.io")
        }
    }
```
#### pom.xml
```xml
<repositories>
  ...
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```
### Dependency
#### build.gradle
```kotlin
dependencies {
  implementation ("com.github.Areeb-Gillani:vertx-boost-db:0.0.4")
}
```
#### pom.xml
```xml
<dependencies>
  ...
	<dependency>
	    <groupId>com.github.Areeb-Gillani</groupId>
	    <artifactId>vertx-boost-db</artifactId>
	    <version>0.0.4</version>
	</dependency>
</dependencies>
```
# Config
One can add multiple connection configurations in this way, and upon creating the repository, please make sure you pass the right connection name so that if the connection is not available in the application, it will be created.

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
      "dbHost":"192.168.1.10",
      "dbPort": 1521,
      "dbUsername": "root",
      "dbPassword": "password",
      "serviceName":"o19c"
      "dbConnectionPoolSize": 10,
      "dbType": "ORACLE",
      "dbRetryCount": 5,
      "dbRetryInterval": 1000
    }
  }
}
```
 
# Usage
This project out of the box gives CrudRepository, MySQLRepository, MSSQLRepository, PostgresRepository, and OracleRepository which a user can extend. It is preferred to use CrudRepository as its factory can create all types of repos based on the dbType attribute present in the connection configuration. 
 
```java
public class MyRepo extends CrudRepository {
    public MyRepo(String connectionName){
        super(connectionName);
    }
    //Please code other related stuff here.
}
```
```java
public class ExampleCaller {
    MyRepo repo;
    public void someDatabaseOperation(){
        repo = new MyRepo("Primary"); // "Primary" is same identifier you saw in configuration JSON.
        // You can create this object as many times as you want
        // because the parent is initializing the connection on singleton pattern
        // This is independent of connection pool size 
    }
    //Please code other related stuff here.
}
```
#### Note: Please include the specific client driver in your build.gradle in order to make this code work.
```kotlin
    dependencies{
          implementation ("io.vertx:vertx-mysql-client:4.4.5")
    }
```
