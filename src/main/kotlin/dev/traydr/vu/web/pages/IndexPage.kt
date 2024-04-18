package dev.traydr.vu.web.pages

import dev.traydr.vu.utils.hxEncoding
import dev.traydr.vu.utils.hxGet
import dev.traydr.vu.utils.hxPost
import dev.traydr.vu.web.components.*
import kotlinx.html.*

fun HTML.indexPage() {
    header("File Manipulator", "Just a manipulator")
    body {
        classes = setOf("")
        navbar()
        wrapper("Submitting Images") {
            div {
                classes = setOf("container", "mx-auto", "grid", "grid-cols-2", "gap-4")
                div {
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