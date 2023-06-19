plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "es.drodriguez"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.litote.kmongo:kmongo:4.7.1")
    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.17")
    implementation("io.github.reactivecircus.cache4k:cache4k:0.9.0")



}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}