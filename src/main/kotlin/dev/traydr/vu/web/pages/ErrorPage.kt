package dev.traydr.vu.web.pages


import dev.traydr.vu.web.components.footer
import dev.traydr.vu.web.components.header
import dev.traydr.vu.web.components.navbar
import dev.traydr.vu.web.components.wrapper
import kotlinx.html.*

fun HTML.errorPage(text: String, httpError: Int) {
    header("FM | Error $httpError", text)
    body {
        classes = setOf()
        navbar()
        wrapper(text) {

        }
        footer()
    }
}