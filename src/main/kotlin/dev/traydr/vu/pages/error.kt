package dev.traydr.vu.pages


import dev.traydr.vu.components.footer
import dev.traydr.vu.components.header
import dev.traydr.vu.components.navbar
import kotlinx.html.*

fun HTML.errorPage(text: String, httpError: Int) {
    header("Titas | Error $httpError", text)
    body {
        classes = setOf()
        navbar()
        div {
            classes = setOf(
                "flex",
                "flex-col",
                "min-h-screen",
            )

            h1 {
                classes= setOf("text-3xl", "text-center", "font-bold", "text-white", "p-10")
                +text
            }
        }
        footer()
    }
}