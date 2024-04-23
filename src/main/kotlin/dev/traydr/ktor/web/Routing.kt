package dev.traydr.ktor.web

import dev.traydr.ktor.domain.GlobalPair
import dev.traydr.ktor.domain.exceptions.UnsupportedFileExtension
import dev.traydr.ktor.domain.service.GlobalPairsService
import dev.traydr.ktor.domain.service.TokenService
import dev.traydr.ktor.domain.service.UserService
import dev.traydr.ktor.utils.acceptedUploadExtension
import dev.traydr.ktor.utils.uploadPath
import dev.traydr.ktor.web.components.gsFormPost
import dev.traydr.ktor.web.components.gsFormPut
import dev.traydr.ktor.web.pages.databasePage
import dev.traydr.ktor.web.pages.errorPage
import dev.traydr.ktor.web.pages.imagePage
import dev.traydr.ktor.web.pages.indexPage
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.body
import kotlinx.html.div
import org.jsoup.Jsoup
import org.jsoup.safety.Safelist
import org.koin.ktor.ext.inject
import java.io.File

fun Application.configureRouting() {
    val tokenService by inject<TokenService>()
    val userService by inject<UserService>()
    val globalPairsService by inject<GlobalPairsService>()

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
    // Static Routes
    routing {
        get("/") {
            call.respondHtml(HttpStatusCode.OK) {
                indexPage()
            }
        }
        get("/images") {
            call.respondHtml(HttpStatusCode.OK) {
                imagePage()
            }
        }
        get("/db") {
            call.respondHtml(HttpStatusCode.OK) {
                databasePage()
            }
        }
        get("/healthcheck") {
            call.respond(HttpStatusCode.OK)
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
    // API routes
    routing {
        get("/api/v1/global") {
            val key: String = call.request.queryParameters["key"].toString()
            val pair: GlobalPair? = globalPairsService.get(key)
            call.respondHtml {
                body {
                    div {
                        if (pair != null) {
                            +"$key : ${pair.value}"
                        } else {
                            +"$key does not have a pair"
                        }
                    }
                }
            }
        }
        post("/api/v1/global") {
            val params = call.receiveParameters()
            val key: String = params["key"].toString()
            val value: String = params["value"].toString()

            if (key.isEmpty() || value.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "Key or Value is missing")
            } else if (key.length > 255 || value.length > 255) {
                call.respond(HttpStatusCode.BadRequest, "Key or Value is above char limit")
            }

            globalPairsService.create(key, Jsoup.clean(value, Safelist.none()))
            call.respondHtml(HttpStatusCode.OK) {
                body {
                    gsFormPost()
                }
            }
        }
        put("/api/v1/global") {
            val params = call.receiveParameters()
            val key: String = params["key"].toString()
            val value: String = params["value"].toString()

            if (key.isEmpty() || value.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "Key or Value is missing")
            } else if (key.length > 255 || value.length > 255) {
                call.respond(HttpStatusCode.BadRequest, "Key or Value is above char limit")
            }

            globalPairsService.update(key, Jsoup.clean(value, Safelist.none()))
            call.respondHtml(HttpStatusCode.OK) {
                body {
                    gsFormPut()
                }
            }
        }
        post("/api/v1/upload") {
            var fileDescription = ""
            var fileName = ""
            val multipartData = call.receiveMultipart()

            try {
                multipartData.forEachPart { partData ->
                    when (partData) {
                        is PartData.FormItem -> {
                            fileDescription = partData.value
                        }

                        is PartData.FileItem -> {
                            val fileBytes = partData.streamProvider().readBytes()
                            val fileExtension = partData.originalFileName?.takeLastWhile { it != '.' }

                            if (!acceptedUploadExtension.contains(fileExtension)) {
                                throw UnsupportedFileExtension("File extension '$fileExtension' is not supported")
                            }

//                            fileName = UUID.randomUUID().toString() + "." + fileExtension
                            fileName = partData.originalFileName ?: ("default$fileExtension")
                            val folder = File(uploadPath)
                            folder.mkdir()
                            File("$uploadPath$fileName").writeBytes(fileBytes)
                        }

                        else -> {}
                    }
                    partData.dispose()
                }
            }
            catch (e: Exception) {
                File("upload/$fileName").delete()

                if (e is UnsupportedFileExtension) {
                    call.respond(HttpStatusCode.NotAcceptable, e.message.toString())
                }
                call.respond(HttpStatusCode.InternalServerError,"Error")
            }

            call.respondText("$fileDescription is uploaded to 'uploads/$fileName'")
        }
        get("/api/v1/download/{name}") {
            val filename = call.parameters["name"]!!
            val file = File("$uploadPath$filename")

            if (file.exists()) {
                call.response.header("Content-Disposition", "attachment; filename=\"${file.name}\"")
                call.response.header("HX-Redirect", "/api/v1/download/$filename")
                call.respondFile(file)
            } else call.respond(HttpStatusCode.NotFound)
        }
    }
}
