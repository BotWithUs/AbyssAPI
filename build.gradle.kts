import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
    java
    `maven-publish`
}

group = "abyss.plugin.api"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("kotlin")
        plugin("kotlinx-serialization")
        plugin("org.gradle.maven-publish")
    }
    tasks.withType<JavaCompile> {
        targetCompatibility = "18"
        sourceCompatibility = "16"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "18"
    }

    tasks.test {
        useJUnitPlatform()
    }
}