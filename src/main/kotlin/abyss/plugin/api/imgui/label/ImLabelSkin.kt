package abyss.plugin.api.imgui.label

import abyss.plugin.api.imgui.ImSkin
import kraken.plugin.api.ImGui

class ImLabelSkin(val label: ImLabel) : ImSkin {
    override fun onPaint() {
        ImGui.label(label.text)
    }
}