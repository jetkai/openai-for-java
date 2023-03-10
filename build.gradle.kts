plugins {
    id("java")
    id("jacoco")
}

group = "io.github.jetkai"
version = "1.1.2"

java {
    //withSourcesJar()
    //withJavadocJar()
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

/**
 * Character Encoding
 * --- START ---
 */
tasks.withType<JavaExec> {
    jvmArgs = listOf("-Dfile.encoding=UTF-8", "-Dconsole.encoding=UTF-8")
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

tasks.compileJava {
    options.encoding = "UTF-8"
}
/**
 * Character Encoding
 * --- END ---
 */

/**
 * Set Environmental Variables for the OPEN_AI_API_KEY
 * Called by System.getenv("OPEN_AI_API_KEY"); in source-code
 */
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

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("openai-binary.jar")
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

/**
 * Jacoco Testing Plugin
 * --- START ---
 */
tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
    dependsOn(tasks.test)
}
/**
 * Jacoco Testing Plugin
 * --- END ---
 */