package dev.traydr.vu.web.pages

import dev.traydr.vu.utils.hxEncoding
import dev.traydr.vu.utils.hxGet
import dev.traydr.vu.utils.hxPost
import dev.traydr.vu.web.components.footer
import dev.traydr.vu.web.components.header
import dev.traydr.vu.web.components.navbar
import dev.traydr.vu.web.components.wrapper
import kotlinx.html.*

fun HTML.imagePage() {
    header("Image Manipulator", "Upload and download images")
    body {
        navbar()
        wrapper("Submitting Images") {
            div {
                classes = setOf("container", "mx-auto", "grid", "grid-cols-2", "gap-4")
                div {
                    classes = setOf("flex", "justify-center")
                    form {
                        hxPost("/api/v2/upload")
                        hxEncoding("multipart/form-data")

                        input {
                            classes = setOf("file-input", "w-full", "max-w-xs")
                            type = InputType.file
                        }

                        button {
                            classes = setOf("btn btn-primary")
                            type = ButtonType.submit
                            +"Upload"
                        }
                    }
                }
                div {
                    classes = setOf("flex", "justify-center")
                    button {
                        classes = setOf("btn btn-primary")
                        hxGet("/api/v1/download/file.txt")
                        +"Download"
                    }
                }
            }
        }
        footer()
    }
}