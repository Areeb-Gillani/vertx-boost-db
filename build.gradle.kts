plugins {
    id("java")
    id("maven-publish")
}

group = "io.github.areebgillani"
version = "0.0.1"
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "io.github.areebgillani"
            artifactId = rootProject.name
            version = "0.0.1"

            from(components["java"])
        }
    }
}
repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.vertx:vertx-web:4.4.4")
    compileOnly("io.vertx:vertx-mysql-client:4.4.4")
    compileOnly("io.vertx:vertx-pg-client:4.4.4")
    compileOnly("io.vertx:vertx-oracle-client:4.4.4")
    compileOnly("io.vertx:vertx-mssql-client:4.4.4")
    implementation("io.vertx:vertx-sql-client-templates:4.4.4")
    implementation("org.projectlombok:lombok:1.18.28")

}
