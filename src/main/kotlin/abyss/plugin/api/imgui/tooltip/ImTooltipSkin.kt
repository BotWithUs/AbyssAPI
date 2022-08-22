package abyss.plugin.api.imgui.tooltip

import abyss.plugin.api.imgui.ImSkin
import kraken.plugin.api.ImGui

class ImTooltipSkin(val tooltip: ImTooltip) : ImSkin {
    override fun onPaint() {
        if(ImGui.isItemHovered()) {
            ImGui.beginTooltip()
            if(tooltip.graphic == null) {
                ImGui.label(tooltip.text)
            } else {
                tooltip.graphic.getSkin().onPaint()
            }
            ImGui.endTooltip()
        }
    }
}