package dev.traydr.vu.web.components

import dev.traydr.vu.utils.hxPost
import dev.traydr.vu.utils.hxPut
import kotlinx.html.*

fun FlowContent.gsFormPost() = div {
    form {
        hxPost("/api/v1/global")

        input {
            name = "key"
            id = "input-key"
            type = InputType.text
            placeholder = "Key"
            classes = setOf("input", "input-bordered", "w-full", "max-w-s")
        }

        input {
            name = "value"
            id = "input-value"
            type = InputType.text
            placeholder = "Value"
            classes = setOf("input", "input-bordered", "w-full", "max-w-s")
        }

        button {
            classes = setOf("btn btn-primary")
            +"Submit"
        }
    }
}

fun FlowContent.gsFormPut() = div {
    form {
        hxPut("/api/v1/global")

        input {
            name = "key"
            id = "existing-key"
            type = InputType.text
            placeholder = "Key"
            classes = setOf("input", "input-bordered", "w-full", "max-w-s")
        }

        input {
            name = "value"
            id = "new-value"
            type = InputType.text
            placeholder = "Value"
            classes = setOf("input", "input-bordered", "w-full", "max-w-s")
        }

        button {
            classes = setOf("btn btn-primary")
            +"Update"
        }
    }
}