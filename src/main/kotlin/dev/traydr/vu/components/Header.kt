package dev.traydr.vu.components

import kotlinx.html.HTML
import kotlinx.html.*

fun HTML.header(title: String, description: String) {
    head {
        meta { charset = "UTF-8" }
        title { +title }
        meta {
            name = "description"
            content = description
        }
        meta {
            name = "viewport"
            content = "width=devide-width, initial-scale=1.0"
        }

        // Css and js libs
        link {
            href = "/css"
            rel = "stylesheet"
            type = "text/css"
        }
        script {
            src = "https://unpkg.com/htmx.org@1.9.11"
            integrity = "sha384-0gxUXCCR8yv9FM2b+U3FDbsKthCI66oH5IA9fHppQq9DDMHuMauqq1ZHBpJxQ0J0"
            attributes["crossorigin"] = "anonymous"
        }
    }
}