plugins {
    java
    jacoco
    id("io.franzbecker.gradle-lombok") version "4.0.0"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "com.github.yukon39"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitVersion = "5.6.1"
val jacksonVersion = "2.11.1"

dependencies {

    implementation( "org.eclipse.lsp4j", "org.eclipse.lsp4j.debug", "0.9.0")

    implementation("info.picocli", "picocli", "4.4.0")

    implementation("org.slf4j", "slf4j-api", "1.8.0-beta4")
    implementation("org.slf4j", "slf4j-simple", "1.8.0-beta4")

    implementation("com.fasterxml.jackson.core", "jackson-databind", jacksonVersion)
    implementation("com.fasterxml.jackson.datatype", "jackson-datatype-jsr310", jacksonVersion)
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-xml", jacksonVersion)

    implementation("org.jetbrains", "annotations", "19.0.0")

    implementation("jakarta.xml.bind", "jakarta.xml.bind-api", "+")
    implementation("org.glassfish.jaxb", "jaxb-runtime", "3.0.0-M4")


    implementation("com.google.code.findbugs", "jsr305", "3.0.2")
    implementation("com.intellij", "annotations", "12.0")

    compileOnly("org.projectlombok", "lombok", lombok.version)

    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-params", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)

    testImplementation("org.assertj", "assertj-core", "3.16.1")
    testImplementation("com.ginsberg", "junit5-system-exit", "1.0.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-Xlint:unchecked")
    options.compilerArgs.add("-Xlint:deprecation")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.github.yukon39.bsl.debugserver.BSLDebugLauncher"
        attributes["Implementation-Version"] = archiveVersion.get()
    }

    enabled = false

    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    project.configurations.implementation.get().isCanBeResolved = true
    configurations = listOf(project.configurations["implementation"])
    archiveClassifier.set("")
}

