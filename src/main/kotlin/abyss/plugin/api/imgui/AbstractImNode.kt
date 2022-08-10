package abyss.plugin.api.imgui

import javafx.beans.property.SimpleBooleanProperty
import abyss.plugin.api.imgui.fx.getValue


abstract class AbstractImNode(internal var parent: AbstractImNode? = null) : ImNode {

    val hiddenProperty = SimpleBooleanProperty(false)
    val hidden by hiddenProperty

    fun getParent() : AbstractImNode? = parent

}