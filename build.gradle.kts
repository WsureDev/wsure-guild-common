plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
}

group = "top.wsure.guild"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(kotlin("stdlib"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.0-native-mt")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    api("com.squareup.okhttp3:okhttp:4.9.3")
    api("org.slf4j:slf4j-api:1.7.32")
    api(kotlin("reflect"))
}