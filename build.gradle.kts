plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.mindwork"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Kotlin support
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // langchain4j
//    implementation("dev.langchain4j:langchain4j-spring-boot-starter:0.36.2")
    implementation("dev.langchain4j:langchain4j-easy-rag:0.36.2")
    implementation("dev.langchain4j:langchain4j-ollama:0.36.2")
    implementation("dev.langchain4j:langchain4j-chroma:0.36.2")

    // JSON
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Тесты
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
