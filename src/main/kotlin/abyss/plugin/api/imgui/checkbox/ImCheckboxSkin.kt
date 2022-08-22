package abyss.plugin.api.imgui.checkbox

import abyss.plugin.api.imgui.ImSkin
import kraken.plugin.api.ImGui

class ImCheckboxSkin(val checkbox: ImCheckbox) : ImSkin {
    override fun onPaint() {
        checkbox.isCheckedProperty.set(ImGui.checkbox(checkbox.text, checkbox.isChecked))
        if(checkbox.tooltip != null) {
            checkbox.tooltip.getSkin().onPaint()
        }
    }
}