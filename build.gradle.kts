plugins {
    id("java")
    id("maven-publish")
}

group = "io.github.jetkai"
version = "1.1.0"

val apiKey = property("OPEN_AI_API_KEY") as String
val organization = property("OPEN_AI_ORGANIZATION") as String
val jiraUsername = property("JIRA_USERNAME") as String
val jiraPassword = property("JIRA_PASSWORD") as String


java {
    withSourcesJar()
    withJavadocJar()
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

    publications {
        create<MavenPublication>("openai") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = jiraUsername
                password = jiraPassword
            }
        }
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.register("setEnvironmentVariable") {
    doFirst {
        val env = System.getenv().toMutableMap()
        env["OPEN_AI_API_KEY"] = apiKey
        env["OPEN_AI_ORGANIZATION"] = organization
        val processBuilder = ProcessBuilder()
        processBuilder.environment().putAll(env)
    }
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    archiveFileName.set("openai-binary.jar")
    manifest {
        attributes["Main-Class"] = "io.github.jetkai.openai.Main"
    }

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}