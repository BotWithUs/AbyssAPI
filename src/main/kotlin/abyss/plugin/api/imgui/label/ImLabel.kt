package abyss.plugin.api.imgui.label

import abyss.plugin.api.imgui.AbstractImNode
import abyss.plugin.api.imgui.ImColor
import abyss.plugin.api.imgui.ImSkin
import javafx.beans.property.SimpleStringProperty
import abyss.plugin.api.imgui.fx.getValue
import abyss.plugin.api.imgui.fx.setValue
import javafx.beans.property.SimpleObjectProperty

open class ImLabel(text: String = "") : AbstractImNode() {

    val textProperty = SimpleStringProperty(text)
    val text by textProperty

    val textColorProperty = SimpleObjectProperty<ImColor>()
    var textColor by textColorProperty

    override fun getSkin(): ImSkin {
        return ImLabelSkin(this)
    }
}