package dev.traydr.vu.web.components

import kotlinx.html.FlowContent
import kotlinx.html.*

fun FlowContent.footer() = footer {
    classes = setOf(
        "footer",
        "footer-center",
        "bg-base-100/75",
        "mt-auto",
        "text-xl",
        "outline",
        "outline-offset-0",
        "outline-blue-800"
    )
    aside {
        p {
            classes = setOf("font-bold")
            +"Made by Titas Lukaitis"
        }
    }
}