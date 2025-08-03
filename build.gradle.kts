
plugins {
    val kotlinVersion = "2.0.0"
    kotlin("jvm") version kotlinVersion // kotlin compiler
    kotlin("plugin.spring") version kotlinVersion // open classes for @Service, @Configuration annotations
    id("org.springframework.boot") version "3.4.4" // add Spring Boot Gradle Plugin (bootRun, bootJar tasks)
    id("io.spring.dependency-management") version "1.1.7"
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("maratmingazovr:spring-boot-3-starter-weather:1.0.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0") // http://localhost:8080/swagger-ui.html
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        maven {
            name = "Central Portal Snapshots"
            url = uri("https://central.sonatype.com/repository/maven-snapshots/")
        }
    }
}
