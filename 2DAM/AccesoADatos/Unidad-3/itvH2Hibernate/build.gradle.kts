plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.6.0"

    application

}

group = "es.drodriguez"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.h2database:h2:2.1.214")
    implementation("org.hibernate:hibernate-core:5.6.14.Final")
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")

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