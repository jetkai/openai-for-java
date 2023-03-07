plugins {
    id("java")
    id("maven-publish")
    id("jacoco")
}

group = "io.github.jetkai"
version = "1.1.0"

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
            val jiraUsername = System.getenv("JIRA_USERNAME") ?: ""
            val jiraPassword = System.getenv("JIRA_PASSWORD") ?: ""
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
        env["OPEN_AI_API_KEY"] = property("open.ai.api.key") as String
        env["OPEN_AI_ORGANIZATION"] = property("open.ai.organization") as String
        env["JIRA_USERNAME"] = property("jira.username") as String
        env["JIRA_PASSWORD"] = property("jira.password") as String
        val processBuilder = ProcessBuilder()
        processBuilder.environment().putAll(env)
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
    dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    archiveFileName.set("openai-binary.jar")

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}