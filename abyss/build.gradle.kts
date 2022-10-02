plugins {
    id("org.openjfx.javafxplugin") version "0.0.10"
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
    implementation("io.ktor:ktor-client-core:2.1.1")
    implementation("io.ktor:ktor-client-cio:2.1.1")
    implementation(project(":filesystem"))
    testImplementation(kotlin("test"))
}

javafx {
    modules("javafx.base")
}

tasks.create("copyJar", Copy::class) {
    from(tasks.withType<Jar>())
    into("C:\\Users\\david\\IdeaProjects\\AbyssFiles")
}

tasks.withType<Jar> {
    finalizedBy(tasks.named("copyJar"))
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