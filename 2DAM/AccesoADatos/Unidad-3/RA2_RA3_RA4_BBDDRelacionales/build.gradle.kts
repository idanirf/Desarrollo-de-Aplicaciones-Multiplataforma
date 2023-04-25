plugins {
    kotlin("jvm") version "1.8.20"
    application
}

group = "es.drodriguez"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.17")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation ("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("org.xerial:sqlite-jdbc:3.41.2.1")
    implementation("io.github.reactivecircus.cache4k:cache4k:0.9.0")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}