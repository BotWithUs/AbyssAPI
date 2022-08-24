package abyss.plugin.api.imgui.tooltip

import abyss.plugin.api.imgui.AbstractImNode
import abyss.plugin.api.imgui.ImColor
import abyss.plugin.api.imgui.ImNode
import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.label.ImLabel
import javafx.beans.property.SimpleObjectProperty
import abyss.plugin.api.imgui.fx.getValue
import abyss.plugin.api.imgui.fx.setValue
import javafx.beans.property.SimpleStringProperty

open class ImTooltip(text: String) : ImNode {

    val graphicProperty = SimpleObjectProperty<AbstractImNode>()
    val graphic by graphicProperty

    val textProperty = SimpleStringProperty(text)
    val text by textProperty

    val textColorProperty = SimpleObjectProperty<ImColor>()
    var textColor by textColorProperty

    override fun getSkin(): ImSkin {
        return ImTooltipSkin(this)
    }
}