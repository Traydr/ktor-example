package dev.traydr.vu.web.pages

import dev.traydr.vu.utils.hxEncoding
import dev.traydr.vu.utils.hxGet
import dev.traydr.vu.utils.hxPost
import dev.traydr.vu.web.components.*
import kotlinx.html.*

fun HTML.databasePage() {
    header("Db Manipulation", "Manipulating the data in the db")
    body {
        navbar()
        wrapper("Manipulating the Database") {

        }
        footer()
    }
}