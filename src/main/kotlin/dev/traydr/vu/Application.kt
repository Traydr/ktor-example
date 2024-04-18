package dev.traydr.vu

import dev.traydr.vu.config.DbConfig
import dev.traydr.vu.domain.repository.GlobalPairsRepository
import dev.traydr.vu.domain.repository.TokenRepository
import dev.traydr.vu.domain.repository.UserRepository
import dev.traydr.vu.domain.service.GlobalPairsService
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
        single { GlobalPairsRepository() }
    }

    val serviceModule = org.koin.dsl.module {
        single { TokenService(get(), get()) }
        single { UserService(get()) }
        single { GlobalPairsService(get()) }
    }

    install(Koin) {
        modules(repoModule, serviceModule)
    }

    val postgresIp = System.getenv("PG_IP")
    val postgresUsername = System.getenv("PG_USERNAME")
    val postgresPassword = System.getenv("PG_Password")
    DbConfig.setup(
        "jdbc:postgresql://$postgresIp:5432/postgres",
        postgresUsername,
        postgresPassword
    )

    configureSecurity()
    configureSerialization()
    configureRouting()
}
