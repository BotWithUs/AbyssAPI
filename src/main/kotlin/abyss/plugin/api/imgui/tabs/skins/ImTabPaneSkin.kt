package abyss.plugin.api.imgui.tabs.skins

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.tabs.ImTabPane
import kraken.plugin.api.ImGui

class ImTabPaneSkin(val pane: ImTabPane) : ImSkin {
    override fun onPaint() {
        if(ImGui.beginTabBar(pane.toString())) {
            pane.tabs.forEach { it.getSkin().onPaint() }
            ImGui.endTabBar()
        }
    }
}