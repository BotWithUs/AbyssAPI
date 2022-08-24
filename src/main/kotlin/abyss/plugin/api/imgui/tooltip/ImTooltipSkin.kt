package abyss.plugin.api.imgui.tooltip

import abyss.plugin.api.imgui.ImSkin
import kraken.plugin.api.ImGui

class ImTooltipSkin(val tooltip: ImTooltip) : ImSkin {
    override fun onPaint() {
        if(ImGui.isItemHovered()) {
            ImGui.beginTooltip()
            if(tooltip.graphic == null) {
                if(tooltip.textColor != null) {
                    ImGui.pushStyleColor(
                        ImGui.ColorStyle.ImGuiCol_Text,
                        tooltip.textColor.red,
                        tooltip.textColor.green,
                        tooltip.textColor.blue,
                        tooltip.textColor.alpha,
                    )
                }
                ImGui.label(tooltip.text)
                if(tooltip.textColor != null) {
                    ImGui.popStyleColor()
                }
            } else {
                tooltip.graphic.getSkin().onPaint()
            }
            ImGui.endTooltip()
        }
    }
}