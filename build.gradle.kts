plugins {
    java
    application
}

group = "ru.senin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    mainClass = "ru.senin.library.LibraryApplication"
}

tasks.test {
    useJUnitPlatform()
}
