package dev.traydr.ktor.web.pages


import dev.traydr.ktor.web.components.footer
import dev.traydr.ktor.web.components.header
import dev.traydr.ktor.web.components.navbar
import dev.traydr.ktor.web.components.wrapper
import kotlinx.html.*

fun HTML.errorPage(text: String, httpError: Int) {
    header("KT | Error $httpError", text)
    body {
        classes = setOf()
        navbar()
        wrapper(text) {

        }
        footer()
    }
}