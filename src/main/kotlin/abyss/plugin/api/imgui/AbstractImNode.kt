package abyss.plugin.api.imgui

import javafx.beans.property.SimpleBooleanProperty
import abyss.plugin.api.imgui.fx.getValue
import abyss.plugin.api.imgui.tooltip.ImTooltip
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty


abstract class AbstractImNode(internal var parent: AbstractImNode? = null) : ImNode {

    val idProperty = SimpleStringProperty()
    val id by idProperty
    val hiddenProperty = SimpleBooleanProperty(false)
    val hidden by hiddenProperty

    val tooltipProperty = SimpleObjectProperty<ImTooltip>()
    val tooltip by tooltipProperty

    fun getParent() : AbstractImNode? = parent

}