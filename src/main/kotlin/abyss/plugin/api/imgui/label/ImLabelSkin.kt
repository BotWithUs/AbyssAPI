package abyss.plugin.api.imgui.label

import abyss.plugin.api.imgui.ImSkin
import kraken.plugin.api.ImGui

class ImLabelSkin(val label: ImLabel) : ImSkin {
    override fun onPaint() {
        if(label.textColor != null) {
            ImGui.pushStyleColor(
                ImGui.ColorStyle.ImGuiCol_Text,
                label.textColor.red,
                label.textColor.green,
                label.textColor.blue,
                label.textColor.alpha,
            )
        }
        ImGui.label(label.text)
        if(label.textColor != null) {
            ImGui.popStyleColor()
        }
        if(label.tooltip != null) {
            label.tooltip.getSkin().onPaint()
        }
    }
}