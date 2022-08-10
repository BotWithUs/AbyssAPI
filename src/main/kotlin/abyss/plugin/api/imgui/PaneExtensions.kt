package abyss.plugin.api.imgui

import abyss.plugin.api.imgui.button.ImButton
import abyss.plugin.api.imgui.containers.ImPane

fun ImPane.button(text: String, apply: ImButton.() -> Unit) {
    val button = ImButton(text)
    button.apply()
    addNode(button)
}