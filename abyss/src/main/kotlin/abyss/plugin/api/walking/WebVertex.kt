package abyss.plugin.api.walking

import abyss.plugin.api.world.WorldTile

class WebVertex(val id: Int, val tile: WorldTile) {

    val edges = mutableListOf<WebEdge>()
    var cost = 0.0
    var parent: WebVertex? = null

    fun findEdge(from: WebVertex, to: WebVertex) : WebEdge? {
        for (edge in edges) {
            if(edge.a.id == from.id && edge.b.id == to.id) {
                return edge
            }
        }
        return null
    }

    operator fun component1() = id
    operator fun component2() = tile
    operator fun component3() = this

}