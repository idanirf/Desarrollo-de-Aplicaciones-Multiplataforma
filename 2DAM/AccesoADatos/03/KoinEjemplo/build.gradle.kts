plugins {
    kotlin("jvm") version "1.8.0"
    application
    id("com.google.devtools.ksp") version "1.8.0-1.0.9"

}

group = "es.drodriguez"
version = "1.0-SNAPSHOT"



repositories {
        mavenCentral()
    }

dependencies {
        testImplementation(kotlin("test"))
        //Koin
        implementation("io.insert-koin:koin-core:3.3.2")
        //Anotaciones Koin y KSP
        ksp("io.insert-koin:koin-ksp-compiler:1.1.0")
        implementation ("io.insert-koin:koin-annotations:1.1.0")

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