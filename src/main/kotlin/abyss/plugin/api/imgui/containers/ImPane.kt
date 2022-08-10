package abyss.plugin.api.imgui.containers

import abyss.plugin.api.imgui.AbstractImNode
import abyss.plugin.api.imgui.ImSkin
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections

open class ImPane : AbstractImNode() {

    val children = SimpleListProperty<AbstractImNode>(FXCollections.observableArrayList())

    fun addNode(index: Int, node: AbstractImNode) {
        node.parent = this
        children.add(index, node)
    }

    fun addNode(node: AbstractImNode) {
        node.parent = this
        children.add(node)
    }

    fun removeNode(node: AbstractImNode) {
        val index = children.indexOf(node)
        val toRemove = children[index]
        toRemove.parent = null
        children.remove(toRemove)
    }

    override fun getSkin(): ImSkin {
        return ImPaneSkin(this)
    }
}