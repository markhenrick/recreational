plugins {
    kotlin("jvm") version "2.2.21"
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
    testImplementation("org.junit.jupiter:junit-jupiter-params:6.0.1")
    testImplementation("org.assertj:assertj-core:3.27.6")
    // TODO consider moving to Kotest in the future
}

tasks.test {
    useJUnitPlatform()
}
