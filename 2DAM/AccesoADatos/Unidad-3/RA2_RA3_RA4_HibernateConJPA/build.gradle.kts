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
    implementation("org.hibernate:hibernate-core:5.6.14.Final")
    implementation("com.h2database:h2:2.1.214")

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