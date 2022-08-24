package abyss.plugin.api.imgui.input.skins

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.input.ImTextField
import kraken.plugin.api.ImGui

class ImPasswordFieldSkin(val textField: ImTextField) : ImSkin {
    private val textByteArray = ByteArray(textField.inputLength.toInt())

    override fun onPaint() {
        if(textField.textColor != null) {
            ImGui.pushStyleColor(
                ImGui.ColorStyle.ImGuiCol_Text,
                textField.textColor.red,
                textField.textColor.green,
                textField.textColor.blue,
                textField.textColor.alpha,
            )
        }
        ImGui.inputPassword(textField.text, textByteArray)
        if(textField.textColor != null) {
            ImGui.popStyleColor()
        }
        if(!textByteArray.contentEquals(textField.input.toByteArray())) {
            textField.inputProperty.set(String(textByteArray))
        }
        if(textField.tooltip != null) {
            textField.tooltip.getSkin().onPaint()
        }
    }
}