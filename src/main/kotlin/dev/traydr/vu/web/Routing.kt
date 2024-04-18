package dev.traydr.vu.web

import dev.traydr.vu.domain.service.TokenService
import dev.traydr.vu.domain.service.UserService
import dev.traydr.vu.web.pages.errorPage
import dev.traydr.vu.web.pages.index
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.io.File

fun Application.configureRouting() {
    val tokenService by inject<TokenService>()
    val userService by inject<UserService>()

    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondHtml(status = status) { errorPage("404: Page Not Found", 404) }
        }
        exception<Throwable> { call, cause ->
            call.respondHtml(status = HttpStatusCode.InternalServerError) {
                errorPage(
                    "500: Internal Server Error",
                    500
                )
            }
        }
    }
    routing {
        get("/") {
            call.respondHtml(HttpStatusCode.OK) {
                index()
            }
        }
        post("/api/v1/upload") {
            val multipart = call.receiveMultipart()
            multipart.forEachPart { part ->
                if (part is PartData.FileItem) {
                    val name = part.originalFileName!!
                    val file = File("/uploads/$name")

                    part.streamProvider().use { its ->
                        file.outputStream().buffered().use {
                            its.copyTo(it)
                        }
                    }
                }
                part.dispose()
            }
            call.response.status(HttpStatusCode.OK)
        }
        post("/api/v2/upload") {
            var fileDescription = ""
            var fileName = ""
            val multipartData = call.receiveMultipart()

            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        fileDescription = part.value
                    }

                    is PartData.FileItem -> {
                        fileName = part.originalFileName as String
                        val fileBytes = part.streamProvider().readBytes()
                        File("uploads/$fileName").writeBytes(fileBytes)
                    }

                    else -> {}
                }
                part.dispose()
            }

            call.respondText("$fileDescription is uploaded to 'uploads/$fileName'")
        }
        get("/api/v1/download/{name}") {
            val filename = call.parameters["name"]!!
            val file = File("/uploads/$filename")

            if (file.exists()) {
                call.response.header("Content-Disposition", "attachment; filename=\"${file.name}\"")
                call.respondFile(file)
            } else call.respond(HttpStatusCode.NotFound)
        }
        get("/robots.txt") {
            call.respondText {
                """
                User-agent: *
                Disallow: /api/
                Crawl-delay: 4
                """.trimIndent()
            }
        }
        staticFiles("/css", File("src/main/resources/css/styles.css"))
    }
}
