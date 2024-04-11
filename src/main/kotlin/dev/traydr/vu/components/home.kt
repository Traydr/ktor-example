package dev.traydr.vu.components

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
                hxPost("/api/v1/file")
                h1 {
                    +"Upload a file"
                }

                input {
                    classes = setOf("file-input", "w-full", "max-w-xs")
                    type = InputType.file
                }
            }
        }
        div {
            +"Download the file"
            button {
                hxGet("/css")
                +"Download"
            }
        }
    }
}