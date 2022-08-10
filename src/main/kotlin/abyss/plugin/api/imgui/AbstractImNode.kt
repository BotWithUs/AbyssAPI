package abyss.plugin.api.imgui

abstract class AbstractImNode(internal var parent: AbstractImNode? = null) : ImNode {

    fun getParent() : AbstractImNode? = parent

}