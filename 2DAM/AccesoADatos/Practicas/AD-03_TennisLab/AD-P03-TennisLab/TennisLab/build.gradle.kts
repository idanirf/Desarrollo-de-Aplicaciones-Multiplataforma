import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application

    id("com.google.devtools.ksp") version "1.7.21-1.0.8"

    kotlin("plugin.serialization") version "1.7.20"

    // SQLdelight
    id("com.squareup.sqldelight") version "1.5.4"
}

group = "org.example"
version = "1.0-SNAPSHOT"
val koin_version= "3.3.2"
val koin_ksp_version= "1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")
    implementation("ch.qos.logback:logback-classic:1.4.5")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("org.litote.kmongo:kmongo:4.7.1")

    implementation("org.litote.kmongo:kmongo-async:4.7.2")
    implementation("org.litote.kmongo:kmongo-coroutine:4.7.2")

    implementation("io.ktor:ktor-client-serialization:2.1.3")
    implementation("io.ktor:ktor-client-content-negotiation:2.1.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.3")

    ksp("de.jensklingenberg.ktorfit:ktorfit-ksp:1.0.0-beta16")
    implementation("de.jensklingenberg.ktorfit:ktorfit-lib:1.0.0-beta16")


    // SqlDeLight
    implementation("com.squareup.sqldelight:runtime:1.5.4")
    // SQLite para SqlDeLight native
    implementation("com.squareup.sqldelight:sqlite-driver:1.5.4")
    // Para poder usar corrutias en SqlDeLight y conectarnos a la base de datos para cambios
    implementation("com.squareup.sqldelight:coroutines-extensions-jvm:1.5.4")
    // Koin Core features
    //Koin
    implementation("io.insert-koin:koin-core:3.3.2")
    //Anotaciones Koin y KSP
    ksp("io.insert-koin:koin-ksp-compiler:1.1.0")
    implementation ("io.insert-koin:koin-annotations:1.1.0")

    implementation("com.google.guava:guava:31.1-jre")



    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")


  
}

tasks.test {
    useJUnitPlatform()
}




tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}


// Donde vamos a generar el código
buildscript {
    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.4")
    }
}

sqldelight {
    // Debemos colocarlo en el main
    database("AppDatabase") {
        // Como se llama el paquete donde esta el código
        packageName = "database"
    }
}

sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}
