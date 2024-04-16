val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String = "0.41.1"
val kodein_version: String = "7.21.1"
val hikari_version: String = "5.1.0"
val kotlinx_version: String = "0.10.1"

plugins {
    kotlin("jvm") version "1.9.23"
    id("au.id.wale.tailwind") version "0.2.0"
    id("io.ktor.plugin") version "2.3.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.23"
}

group = "dev.traydr.vu"
version = "0.0.1"

tailwind {
    version = "3.4.1"
    configPath = "src/main/resources"
    input = "src/main/resources/tailwind/input.css"
    output = "src/main/resources/css/styles.css"
}

application {
    mainClass.set("dev.traydr.vu.ApplicationKt")

//    val isDevelopment: Boolean = project.ext.has("development")
    val isDevelopment: Boolean = true
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-sessions-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-status-pages-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-html-builder-jvm")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinx_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("com.h2database:h2:2.1.214")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation ("org.kodein.di:kodein-di:$kodein_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("com.zaxxer:HikariCP:$hikari_version")
    implementation("at.favre.lib:bcrypt:{latest-version}")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
