plugins {
    id("java")
}

group = "ru.nsu.palkin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.module:jackson-modules-java8:2.12.4")
    implementation("com.fasterxml.jackson.module:jackson-modules-java8:2.12.4")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.4")
    implementation("args4j:args4j:2.33")
}

tasks.test {
    useJUnitPlatform()
}