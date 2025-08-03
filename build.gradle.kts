val grpcVersion = "1.63.0"
val protobufVersion = "3.25.3"

plugins {
    val kotlinVersion = "2.0.0"
    val protobufPluginVersion = "0.9.4"

    kotlin("jvm") version kotlinVersion // kotlin compiler
    kotlin("plugin.spring") version kotlinVersion // open classes for @Service, @Configuration annotations
    id("org.springframework.boot") version "3.4.4" // add Spring Boot Gradle Plugin (bootRun, bootJar tasks)
    id("io.spring.dependency-management") version "1.1.7"
    id("com.google.protobuf") version protobufPluginVersion
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("maratmingazovr:spring-boot-3-starter-weather:1.0.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0") // http://localhost:8080/swagger-ui.html

    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("javax.annotation:javax.annotation-api:1.3.2") // solve cannot find symbol @javax.annotation.Generated(
    implementation("net.devh:grpc-server-spring-boot-starter:2.15.0.RELEASE")
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


/**
 * PROTOBUF
 * ./gradlew generateProto
 * grpcurl -plaintext -d '{"city":"Paris"}' localhost:9090 weather.WeatherService/GetWeather - test application
 */

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protobufVersion}"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
            }
        }
    }
}

sourceSets {
    main {
        proto {
            srcDir("src/main/proto")
        }
    }
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}



