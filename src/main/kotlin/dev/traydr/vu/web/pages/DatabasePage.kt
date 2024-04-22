package dev.traydr.vu.web.pages

import dev.traydr.vu.utils.*
import dev.traydr.vu.web.components.*
import kotlinx.html.*

fun HTML.databasePage() {
    header("Db Manipulation", "Manipulating the data in the db")
    body {
        navbar()
        wrapper("Global key-value store") {
            div {
                classes = setOf("container", "mx-auto", "grid", "grid-cols-2", "gap-4")
                div {
                    classes = setOf("flex", "justify-center", "grid", "grid-cols-1")

                    h2 {
                        classes = setOf("text-xl", "p-4")
                        +"You will be submitting a key-value pair to a global store"
                    }

                    gsFormPost()
                }
                div {
                    classes = setOf("flex", "justify-center", "grid", "grid-cols-1")

                    h2 {
                        classes = setOf("text-xl", "p-4")
                        +"You will be retrieving a value from the global key-value store"
                    }

                    form {
                        hxGet("/api/v1/global")
                        hxTarget("#state-output")
                        hxSwap("innerHTML")

                        input {
                            name = "key"
                            id = "output-key"
                            type = InputType.text
                            placeholder = "Key"
                            classes = setOf("input", "input-bordered", "w-full", "max-w-s")
                        }

                        button {
                            classes = setOf("btn", "btn-primary")
                            +"Display"
                        }
                    }

                    div {
                        id = "state-output"
                        classes = setOf("text-center", "outline", "outline-offset-0", "outline-orange-500")
                        +"Output will be displayed here"
                    }
                }
                div {
                    classes = setOf("flex", "justify-center", "grid", "grid-cols-1")

                    h2 {
                        classes = setOf("text-xl", "p-4")
                        +"You will be updating a key-value pair to a global store"
                    }

                    gsFormPut()
                }
            }
        }
        footer()
    }
}