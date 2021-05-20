plugins {
    java
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

    implementation("jakarta.xml.bind", "jakarta.xml.bind-api", "3.0.0-RC3")
    implementation("org.glassfish.jaxb", "jaxb-runtime", "3.0.0-M4")

    implementation("org.jetbrains", "annotations", "19.0.0")
    implementation("com.google.code.findbugs", "jsr305", "3.0.2")

    compileOnly("org.projectlombok", "lombok", lombok.version)

    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-params", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)

    testImplementation("org.assertj", "assertj-core", "3.16.1")
    testImplementation("com.ginsberg", "junit5-system-exit", "1.0.0")
}