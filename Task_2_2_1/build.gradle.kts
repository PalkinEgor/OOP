plugins {
    id("java")
    id("jacoco")
}

group = "ru.nsu.palkin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    "jacocoTestReport"(JacocoReport::class) {
        reports {
            xml.required.set(true)
            html.required.set(true)
            html.outputLocation.set(layout.buildDirectory.dir("$buildDir/jacoco/jacocoHtml"))
        }
    }
}