package abyss.plugin.api.imgui.input.skins

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.input.ImIntInputField
import abyss.plugin.api.ImGui

class ImIntInputFieldSkin(val intField: ImIntInputField) : ImSkin {
    override fun onPaint() {
        intField.inputProperty.set(ImGui.intInput(intField.text, intField.input.toInt()))
        if(intField.tooltip != null) {
            intField.tooltip.getSkin().onPaint()
        }
    }
}