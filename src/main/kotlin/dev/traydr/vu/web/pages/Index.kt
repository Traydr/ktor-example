package dev.traydr.vu.web.pages

import dev.traydr.vu.web.components.footer
import dev.traydr.vu.web.components.header
import dev.traydr.vu.web.components.home
import dev.traydr.vu.web.components.navbar
import kotlinx.html.*

fun HTML.index() {
    header("File Manipulator", "Just a manipulator")
    body {
        classes = setOf("")
        navbar()
        div {
            classes = setOf(
                "flex",
                "flex-col",
                "min-h-screen",
            )
            home()
        }
        footer()
    }
}