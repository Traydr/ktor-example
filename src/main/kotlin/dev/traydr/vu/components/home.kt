package dev.traydr.vu.components

import kotlinx.html.FlowContent
import kotlinx.html.classes
import kotlinx.html.*

fun FlowContent.home() = div {
    classes = setOf("container", "gap-4", "justify-center", "p-4")

    div {
        classes = setOf("items-center")
        h1 {
            classes = setOf("text-bold", "text-white")
            +"This is the main content"
        }

        div {
            classes = setOf("md:col-span-2", "lg:col-span-3")

        }
    }
}