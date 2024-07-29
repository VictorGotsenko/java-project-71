plugins {
    id("java")
    id("application")
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.github.ben-manes.versions") version "0.41.0"
    id("checkstyle")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
        mainClass = "hexlet.code.App"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
