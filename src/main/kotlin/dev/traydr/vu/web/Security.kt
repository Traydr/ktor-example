package dev.traydr.vu.web

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*

fun Application.configureSecurity() {
    data class UserSession(val name: String, val count: Int, val state: String) : Principal
    install(Sessions) {
        cookie<UserSession>("user_session") {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 24 * 60 * 60 // 1 day
            cookie.extensions["SameSite"] = "strict"
        }
    }

    install(Authentication) {
        session<UserSession>("auth-session") {
            validate { session ->
                if (session.name.startsWith("jet")) {
                    session
                } else {
                    null
                }
            }
            challenge {
                call.respondRedirect("/login")
            }
        }
    }
}
