plugins {
    id("java")
    id("maven-publish")
}

val vertxVersion = "4.5.9"

group = "io.github.Areeb-Gillani"
version = "0.1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = rootProject.name
            version = rootProject.version.toString()

            from(components["java"])
        }
    }
}
repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    compileOnly("io.vertx:vertx-web:$vertxVersion")
    compileOnly("io.vertx:vertx-mysql-client:$vertxVersion")
    compileOnly("io.vertx:vertx-pg-client:$vertxVersion")
    compileOnly("io.vertx:vertx-oracle-client:$vertxVersion")
    compileOnly("io.vertx:vertx-mssql-client:$vertxVersion")
    implementation("io.vertx:vertx-sql-client-templates:$vertxVersion")
    implementation("org.projectlombok:lombok:1.18.34")

}
