package dev.traydr.vu.web.components

import dev.traydr.vu.utils.hxEncoding
import dev.traydr.vu.utils.hxGet
import dev.traydr.vu.utils.hxPost
import kotlinx.html.FlowContent
import kotlinx.html.classes
import kotlinx.html.*

fun FlowContent.home() = div {
    classes = setOf("container", "mx-auto", "gap-4")

    h1 {
        classes = setOf("text-bold", "text-white", "text-center", "text-3xl", "p-3")
        +"Submitting images"
    }

    div {
        classes = setOf("grid", "grid-cols-2", "gap-4")
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