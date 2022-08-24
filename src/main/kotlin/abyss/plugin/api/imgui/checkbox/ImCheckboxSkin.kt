package abyss.plugin.api.imgui.checkbox

import abyss.plugin.api.imgui.ImSkin
import kraken.plugin.api.ImGui

class ImCheckboxSkin(val checkbox: ImCheckbox) : ImSkin {
    override fun onPaint() {
        if(checkbox.textColor != null) {
            ImGui.pushStyleColor(
                ImGui.ColorStyle.ImGuiCol_Text,
                checkbox.textColor.red,
                checkbox.textColor.green,
                checkbox.textColor.blue,
                checkbox.textColor.alpha,
            )
        }
        checkbox.isCheckedProperty.set(ImGui.checkbox(checkbox.text, checkbox.isChecked))
        if(checkbox.textColor != null) {
            ImGui.popStyleColor()
        }
        if(checkbox.tooltip != null) {
            checkbox.tooltip.getSkin().onPaint()
        }
    }
}