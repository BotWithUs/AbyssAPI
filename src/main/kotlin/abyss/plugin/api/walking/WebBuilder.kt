package abyss.plugin.api.walking

import abyss.plugin.api.actions.MenuAction
import abyss.plugin.api.actions.ObjectAction
import abyss.plugin.api.walking.TraverseWeb.Companion.linkMenuEdge
import abyss.plugin.api.walking.TraverseWeb.Companion.linkMenuEdgeTo
import abyss.plugin.api.walking.TraverseWeb.Companion.linkObjectEdgeTo
import abyss.plugin.api.walking.TraverseWeb.Companion.linkWalkEdge
import abyss.plugin.api.world.WorldTile

class WebBuilder {

    private val web = TraverseWeb()
    private var lastVertex: WebVertex? = null

    fun addTile(tile: WorldTile) {
        val v = WebVertex(web.nextId(), tile)
        web.addVertex(v)
        val last = lastVertex
        if(last != null) {
            linkWalkEdge(last, v)
        }
        lastVertex = v
    }

    fun addTiles(vararg tiles: WorldTile) {
        for (tile in tiles) {
            addTile(tile)
        }
    }

    fun addMenu(tile: WorldTile, action: MenuAction, vararg args: Int) {
        val v = WebVertex(web.nextId(), tile)
        web.addVertex(v)
        val last = lastVertex
        if(last != null) {
            linkMenuEdge(last, v, action, *args)
        }
        lastVertex = v
    }

    fun addMenuTo(tile: WorldTile, action: MenuAction, vararg args: Int) {
        val v = WebVertex(web.nextId(), tile)
        web.addVertex(v)
        val last = lastVertex
        if(last != null) {
            linkMenuEdgeTo(last, v, action, *args)
        }
    }

    fun addObject(tile: WorldTile, action: ObjectAction, objectId: Int, objectTile: WorldTile) {
        val v = WebVertex(web.nextId(), tile)
        web.addVertex(v)
        val last = lastVertex
        if(last != null) {
            linkObjectEdgeTo(last, v, action, objectId, objectTile)
        }
    }

    fun setLastVertex(id: Int) {
        lastVertex = web.findVertexOrNull(id)
    }

    fun build() = web
    fun walk(dest: WorldTile) = WalkEvent(dest, web)

    companion object {
        @JvmStatic
        fun newBuilder() : WebBuilder {
            return WebBuilder()
        }
        @JvmStatic
        fun newBuilder(vararg tiles: WorldTile) : WebBuilder {
            return WebBuilder().apply { addTiles(*tiles) }
        }
        @JvmStatic
        fun walkEvent(dest: WorldTile, vararg tiles: WorldTile) : WalkEvent {
            return WebBuilder().apply { addTiles(*tiles) }.walk(dest)
        }
    }
}