package abyss.plugin.api.imgui

import abyss.plugin.api.imgui.button.ImButton
import abyss.plugin.api.imgui.checkbox.ImCheckbox
import abyss.plugin.api.imgui.containers.ImHorizontalPane
import abyss.plugin.api.imgui.containers.ImPane
import abyss.plugin.api.imgui.input.ImPasswordField
import abyss.plugin.api.imgui.input.ImTextField
import abyss.plugin.api.imgui.label.ImLabel
import javafx.beans.binding.BooleanExpression

fun ImPane.button(text: String, apply: ImButton.() -> Unit = {}) {
    val button = ImButton(text)
    button.apply()
    addNode(button)
}

fun ImPane.checkbox(text: String, apply: ImCheckbox.() -> Unit = {}) {
    val checkbox = ImCheckbox(text)
    checkbox.apply()
    addNode(checkbox)
}


fun ImPane.textfield(text: String, textLength: Int = 50, apply : ImTextField.() -> Unit = {}) {
    val textField = ImTextField(text, textLength)
    textField.apply()
    addNode(textField)
}

fun ImPane.passwordfield(text: String, textLength: Int = 50, apply : ImPasswordField.() -> Unit = {}) {
    val passwordField = ImPasswordField(text, textLength)
    passwordField.apply()
    addNode(passwordField)
}

fun ImPane.label(text: String, apply: ImLabel.() -> Unit = {}) {
    val label = ImLabel(text)
    label.apply()
    addNode(label)
}

fun ImPane.hbox(apply : ImHorizontalPane.() -> Unit) {
    val horizontalPane = ImHorizontalPane()
    horizontalPane.apply()
    addNode(horizontalPane)
}

fun AbstractImNode.hiddenWhen(property: BooleanExpression) {
    hiddenProperty.bind(property)
}