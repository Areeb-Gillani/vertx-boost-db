plugins {
    id("java")
    id("maven-publish")
}

group = "io.github.areebgillani"
version = "0.0.4"
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = rootProject.name
            version = project.version.toString()
            from(components["java"])
        }
    }
}
tasks.publishToMavenLocal {
    dependsOn(tasks.build)
    onlyIf {
        true
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
