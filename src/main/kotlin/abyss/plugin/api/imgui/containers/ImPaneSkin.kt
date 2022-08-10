package abyss.plugin.api.imgui.containers

import abyss.plugin.api.imgui.ImSkin

class ImPaneSkin(val pane: ImPane) : ImSkin {
    override fun onPaint() {
        pane.children.forEach { it.getSkin().onPaint() }
    }
}