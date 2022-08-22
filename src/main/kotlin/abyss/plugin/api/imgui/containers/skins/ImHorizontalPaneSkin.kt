package abyss.plugin.api.imgui.containers.skins

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.containers.ImHorizontalPane
import kraken.plugin.api.ImGui

class ImHorizontalPaneSkin(val horizontalPane: ImHorizontalPane) : ImSkin {
    override fun onPaint() {
        if (horizontalPane.id != null) {
            ImGui.beginChild(
                horizontalPane.id,
                horizontalPane.width.toInt(),
                horizontalPane.height.toInt(),
                horizontalPane.hasBorder
            )
        }
        for (child in horizontalPane.children) {
            if (child.hiddenProperty.not().get()) {
                child.getSkin().onPaint()
                ImGui.sameLine()
            }
        }
        if(horizontalPane.id != null) {
            ImGui.endChild()
        }
    }
}