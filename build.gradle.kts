plugins {
    id("java")
    id("maven-publish")
}

group = "io.github.jetkai"
version = "1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    //Jackson to parse JSON data
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")

    //JUnit for testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

publishing {
    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = property("mavenUsername") as String
                password = property("mavenPassword") as String
                print(username)
            }
        }
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    archiveFileName.set("chatgpt.jar")
    manifest {
        attributes["Main-Class"] = "io.github.jetkai.chatgpt.Main"
    }

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

}