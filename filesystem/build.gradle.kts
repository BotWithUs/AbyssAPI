dependencies {
    implementation("org.apache.commons:commons-compress:1.21")
    implementation("io.netty:netty-buffer:4.1.82.Final")
    implementation("com.github.jponge:lzma-java:1.3")
    implementation("org.tmatesoft.sqljet:sqljet:1.1.14")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.rshub.filesystem"
            artifactId = "RSFileSystem"
            version = "1.0-SNAPSHOT"
            from(components["java"])
        }
    }
}