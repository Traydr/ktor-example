package dev.traydr.vu

import dev.traydr.vu.domain.repository.TokenRepository
import dev.traydr.vu.domain.repository.UserRepository
import dev.traydr.vu.domain.service.TokenService
import dev.traydr.vu.domain.service.UserService
import dev.traydr.vu.web.configureRouting
import dev.traydr.vu.web.configureSecurity
import dev.traydr.vu.web.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin

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
    val repoModule = org.koin.dsl.module {
        single { UserRepository() }
        single { TokenRepository() }
    }

    val serviceModule = org.koin.dsl.module {
        single { TokenService(get(), get()) }
        single { UserService(get()) }
    }

    install(Koin) {
        modules(repoModule, serviceModule)
    }

    configureSecurity()
    configureSerialization()
    configureRouting()
}
