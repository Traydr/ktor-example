package dev.traydr.vu

import dev.traydr.vu.plugins.*
import dev.traydr.vu.web.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module,
        watchPaths = listOf("classes", "resources")
    )
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureTemplating()
    configureDatabases()
    configureRouting()
}
