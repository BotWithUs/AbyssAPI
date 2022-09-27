import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    java
    `maven-publish`
    id("org.openjfx.javafxplugin") version "0.0.10"
}

group = "abyss.plugin.api"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

javafx {
    modules("javafx.base")
}

subprojects {
    apply {
        plugin("kotlin")
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
    implementation("io.ktor:ktor-client-core:2.1.1")
    implementation("io.ktor:ktor-client-cio:2.1.1")
    implementation(project(":filesystem"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    targetCompatibility = "18"
    sourceCompatibility = "16"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "18"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.abyss.api"
            artifactId = "AbyssAPI"
            version = "1.0-SNAPSHOT"
            from(components.getByName("java"))
        }
    }
}