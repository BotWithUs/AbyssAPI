package abyss.plugin.api.imgui.containers

import abyss.plugin.api.imgui.AbstractImNode
import javafx.beans.property.SimpleListProperty

interface ImPane {

    val children: SimpleListProperty<AbstractImNode>

    fun addNode(index: Int, node: AbstractImNode)
    fun addNode(node: AbstractImNode)
    fun removeNode(node: AbstractImNode)

}