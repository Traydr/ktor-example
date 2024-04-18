package dev.traydr.vu.web.components

import kotlinx.html.FlowContent
import kotlinx.html.classes
import kotlinx.html.*

fun FlowContent.navbar() = nav {
    classes = setOf("navbar", "bg-base-100/75", "outline", "outline-offset-0", "outline-blue-800")

    div {
        classes = setOf("flex-1")
        a {
            classes = setOf("btn", "btn-ghost", "text-xl", "text-bold")
            href = "/"
            +"Web Testing"
        }
    }

    div {
        classes = setOf("navbar-center", "tabs", "tabs-boxed", "bg-sky-800")
        ul {
            classes = setOf("menu", "menu-horizontal", "px-1")
            li {
                a {
                    href = "/images"
                    +"images"
                }
            }
            li {
                a {
                    href = "/db"
                    +"db"
                }
            }
        }


    }

    div {
        classes = setOf("navbar-end", "text-xl", "pr-4")
        div {
            +"Welcome!"
        }
    }
}