package abyss.plugin.api.imgui.label

import abyss.plugin.api.imgui.AbstractImNode
import abyss.plugin.api.imgui.ImSkin
import javafx.beans.property.SimpleStringProperty
import abyss.plugin.api.imgui.fx.getValue

open class ImLabel(text: String = "") : AbstractImNode() {

    val textProperty = SimpleStringProperty(text)
    val text by textProperty

    override fun getSkin(): ImSkin {
        return ImLabelSkin(this)
    }
}