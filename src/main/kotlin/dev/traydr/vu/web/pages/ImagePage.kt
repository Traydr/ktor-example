package dev.traydr.vu.web.pages

import dev.traydr.vu.utils.*
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
                        hxPost("/api/v1/upload")
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
                    form {
                        hxGet("/api/v1/download/{fileName}")
                        hxInclude("[name='fileName']")
                        hxExt("path-params")

                        input {
                            classes = setOf("file-input", "w-full", "max-w-xs")
                            name = "fileName"
                            type = InputType.text
                        }

                        button {
                            classes = setOf("btn btn-primary")
                            +"Download"
                        }
                    }
                }
            }
        }
        footer()
    }
}