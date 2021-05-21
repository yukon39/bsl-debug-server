plugins {
    java
    jacoco
    id("io.franzbecker.gradle-lombok")
}

group = "com.github.yukon39"

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
    maven ( "https://jitpack.io" )
}

val junitVersion = "5.6.1"

dependencies {
    implementation(project(":debugBSL"))

    implementation("org.jetbrains", "annotations", "19.0.0")
    implementation("com.google.code.findbugs", "jsr305", "3.0.2")

    implementation("org.slf4j", "slf4j-api", "1.8.0-beta4")
    implementation("org.slf4j", "slf4j-simple", "1.8.0-beta4")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-params", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)

    testImplementation("org.assertj", "assertj-core", "3.16.1")
    testImplementation("com.ginsberg", "junit5-system-exit", "1.0.0")
}

tasks.test {
    systemProperty("file.encoding", "utf-8")
    useJUnitPlatform()
}