package dev.traydr.vu.web.pages

import dev.traydr.vu.utils.hxEncoding
import dev.traydr.vu.utils.hxGet
import dev.traydr.vu.utils.hxPost
import dev.traydr.vu.web.components.*
import kotlinx.html.*

fun HTML.indexPage() {
    header("Index", "The home page")
    body {
        navbar()
        wrapper("Home page") {

        }
        footer()
    }
}