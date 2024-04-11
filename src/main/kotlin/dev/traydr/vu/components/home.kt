package dev.traydr.vu.components

import kotlinx.html.FlowContent
import kotlinx.html.classes
import kotlinx.html.*

fun FlowContent.home() = div {
    classes = setOf("grid", "grid-cols-1", "sm:grid-cols-2", "md:grid-cols-3", "lg:grid-cols-4", "gap-4")

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