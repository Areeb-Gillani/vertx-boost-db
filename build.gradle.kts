plugins {
    id("java")
    id("maven-publish")
}

group = "io.github.areebgillani"
version = "1.0.0"
val vertxVersion="4.5.8"
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
    compileOnly("io.vertx:vertx-web:$vertxVersion")
    compileOnly("io.vertx:vertx-mysql-client:$vertxVersion")
    compileOnly("io.vertx:vertx-pg-client:$vertxVersion")
    compileOnly("io.vertx:vertx-oracle-client:$vertxVersion")
    compileOnly("io.vertx:vertx-mssql-client:$vertxVersion")
    implementation("io.vertx:vertx-sql-client-templates:$vertxVersion")
    implementation("org.projectlombok:lombok:1.18.28")

}
