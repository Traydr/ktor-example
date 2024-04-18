package dev.traydr.vu.web.pages


import dev.traydr.vu.web.components.footer
import dev.traydr.vu.web.components.header
import dev.traydr.vu.web.components.navbar
import kotlinx.html.*

fun HTML.errorPage(text: String, httpError: Int) {
    header("FM | Error $httpError", text)
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