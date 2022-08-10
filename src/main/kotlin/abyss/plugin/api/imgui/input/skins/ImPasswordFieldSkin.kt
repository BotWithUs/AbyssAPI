package abyss.plugin.api.imgui.input.skins

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.input.ImTextField
import kraken.plugin.api.ImGui

class ImPasswordFieldSkin(val textField: ImTextField) : ImSkin {
    private val textByteArray = ByteArray(textField.inputLength.toInt())

    override fun onPaint() {
        ImGui.inputPassword(textField.text, textByteArray)
        if(!textByteArray.contentEquals(textField.input.toByteArray())) {
            textField.inputProperty.set(String(textByteArray))
        }
    }
}