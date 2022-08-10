package abyss.plugin.api.imgui.input

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.label.ImLabel
import javafx.beans.property.SimpleIntegerProperty
import abyss.plugin.api.imgui.fx.getValue
import abyss.plugin.api.imgui.input.skins.ImIntInputFieldSkin


class ImIntInputField(text: String = "", default: Int = 0) : ImLabel(text) {

    val inputProperty = SimpleIntegerProperty(default)
    val input by inputProperty

    override fun getSkin(): ImSkin {
        return ImIntInputFieldSkin(this)
    }
}