plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "es.drodriguez"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo1.maven.org/maven2/")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("org.simpleframework:simple-xml:2.7.1")
    implementation("org.jetbrains.kotlinx:dataframe:0.9.0-dev-1130-0.11.0.165")



}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(16)
}

application {
    mainClass.set("MainKt")
}