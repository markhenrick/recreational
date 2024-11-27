plugins {
    kotlin("jvm") version "2.0.21"
}

group = "site.markhenrick.recreational"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}