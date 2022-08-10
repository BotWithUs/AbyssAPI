package abyss.plugin.api.imgui.containers.skins

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.containers.ImVerticalPane

class ImVerticalPaneSkin(val pane: ImVerticalPane) : ImSkin {
    override fun onPaint() {
        pane.children.forEach {
            if(it.hiddenProperty.not().get()) {
                it.getSkin().onPaint()
            }
        }
    }
}