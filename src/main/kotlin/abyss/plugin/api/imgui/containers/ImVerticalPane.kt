package abyss.plugin.api.imgui.containers

import abyss.plugin.api.imgui.AbstractImNode
import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.containers.skins.ImVerticalPaneSkin
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections

open class ImVerticalPane : AbstractImNode(), ImPane {

    override val children = SimpleListProperty<AbstractImNode>(FXCollections.observableArrayList())

    override fun addNode(index: Int, node: AbstractImNode) {
        node.parent = this
        children.add(index, node)
    }

    override fun addNode(node: AbstractImNode) {
        node.parent = this
        children.add(node)
    }

    override fun removeNode(node: AbstractImNode) {
        val index = children.indexOf(node)
        val toRemove = children[index]
        toRemove.parent = null
        children.remove(toRemove)
    }

    override fun getSkin(): ImSkin {
        return ImVerticalPaneSkin(this)
    }
}