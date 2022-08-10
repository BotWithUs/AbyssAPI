package abyss.plugin.api.imgui.tabs.skins

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.tabs.ImTab
import kraken.plugin.api.ImGui

class ImTabSkin(val tab: ImTab) : ImSkin {
    override fun onPaint() {
        if(tab.hiddenProperty.not().get()) {
            if(ImGui.beginTabItem(tab.title)) {
                tab.content.getSkin().onPaint()
                ImGui.endTabItem()
            }
        }
    }
}