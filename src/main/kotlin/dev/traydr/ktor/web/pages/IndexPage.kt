package dev.traydr.ktor.web.pages

import dev.traydr.ktor.web.components.*
import kotlinx.html.*

fun HTML.indexPage() {
    header("KT | Home", "The home page")
    body {
        navbar()
        wrapper("Home") {

        }
        footer()
    }
}