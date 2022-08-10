package abyss.plugin.api.imgui.containers.skins

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.containers.ImHorizontalPane
import kraken.plugin.api.ImGui

class ImHorizontalPaneSkin(val horizontalPane: ImHorizontalPane) : ImSkin {
    override fun onPaint() {
        for (child in horizontalPane.children) {
            if(child.hiddenProperty.not().get()) {
                child.getSkin().onPaint()
                ImGui.sameLine()
            }
         }
    }
}