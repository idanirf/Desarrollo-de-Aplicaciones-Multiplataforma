plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "es.drodriguez"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
    jvmToolchain(17)

}

application {
    mainClass.set("MainKt")
}