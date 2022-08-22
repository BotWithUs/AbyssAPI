package abyss.plugin.api.imgui.containers

import abyss.plugin.api.imgui.AbstractImNode
import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.containers.skins.ImVerticalPaneSkin
import abyss.plugin.api.imgui.fx.getValue
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections

open class ImVerticalPane(id: String? = null) : AbstractImNode(), ImPane {

    override val children = SimpleListProperty<AbstractImNode>(FXCollections.observableArrayList())

    val widthProperty = SimpleIntegerProperty(0)
    val heightProperty = SimpleIntegerProperty(0)
    val hasBorderProperty = SimpleBooleanProperty(false)
    val width by widthProperty
    val height by heightProperty
    val hasBorder by hasBorderProperty

    init {
        idProperty.set(id)
    }

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