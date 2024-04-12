package dev.traydr.vu.pages

import dev.traydr.vu.components.footer
import dev.traydr.vu.components.header
import dev.traydr.vu.components.home
import dev.traydr.vu.components.navbar
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