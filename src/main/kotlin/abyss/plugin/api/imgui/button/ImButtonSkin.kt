package abyss.plugin.api.imgui.button

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.ImGui

class ImButtonSkin(val button : ImButton) : ImSkin {
    override fun onPaint() {
        if(ImGui.button(button.text)) {
            button.onActionProperty.get()?.invoke(button)
        }
        if(button.tooltip != null) {
            button.tooltip.getSkin().onPaint()
        }
    }
}