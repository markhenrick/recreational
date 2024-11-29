plugins {
    kotlin("jvm") version "2.1.0"
}

kotlin {
    jvmToolchain(21)
}

group = "site.markhenrick.recreational"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.3")
    testImplementation("org.assertj:assertj-core:3.26.3")
    // TODO consider moving to Kotest in the future
}

tasks.test {
    useJUnitPlatform()
}