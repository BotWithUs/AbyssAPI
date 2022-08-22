package abyss.plugin.api.imgui.containers.skins

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.containers.ImVerticalPane
import kraken.plugin.api.ImGui

class ImVerticalPaneSkin(val pane: ImVerticalPane) : ImSkin {
    override fun onPaint() {
        if(pane.id != null) {
            ImGui.beginChild(pane.id, pane.width.toInt(), pane.height.toInt(), pane.hasBorder)
        }
        pane.children.forEach {
            if(it.hiddenProperty.not().get()) {
                it.getSkin().onPaint()
            }
        }
        if(pane.id != null) {
            ImGui.endChild()
        }
    }
}