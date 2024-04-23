package dev.traydr.ktor.web.pages

import dev.traydr.ktor.web.components.*
import kotlinx.html.*

fun HTML.indexPage() {
    header("Index", "The home page")
    body {
        navbar()
        wrapper("Home") {

        }
        footer()
    }
}