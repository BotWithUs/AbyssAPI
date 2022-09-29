package abyss.plugin.api.walking

import abyss.plugin.api.actions.MenuAction
import abyss.plugin.api.actions.ObjectAction
import abyss.plugin.api.extensions.getExt
import abyss.plugin.api.walking.edges.extensions.MenuEdge
import abyss.plugin.api.walking.edges.extensions.ObjectEdge
import abyss.plugin.api.walking.extensions.RequirementExtension
import abyss.plugin.api.world.WorldTile
import abyss.plugin.api.Vector3i
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

class TraverseWeb {

    val vertices = mutableMapOf<Int, WebVertex>()

    fun addVertex(vertex: WebVertex) {
        vertices[vertex.id] = vertex
    }

    fun findVertexOrNull(id: Int): WebVertex? {
        return vertices[id]
    }

    fun findClosest(tile: Vector3i): WebVertex? {
        var closestDist = 0.0
        var closest: WebVertex? = null
        for ((_, vertex) in vertices) {
            if (vertex.tile.z != tile.z)
                continue
            val vtile = vertex.tile
            val distOnAxis = sqrt((vtile.x - tile.x).toDouble().pow(2) + (vtile.y - tile.y).toDouble().pow(2))

            if (closest == null || distOnAxis < closestDist) {
                closest = vertex
                closestDist = distOnAxis
            }
        }
        return closest
    }

    fun findPath(from: WebVertex, to: WebVertex): List<WebVertex> {
        val path = LinkedList<WebVertex>()

        for ((_, vertex) in vertices) {
            vertex.cost = 100000000.0
            vertex.parent = null
        }

        from.parent = null
        from.cost = 0.0

        val setLengthNode = mutableSetOf<WebVertex>()
        setLengthNode.add(from)

        while(setLengthNode.isNotEmpty()) {
            val top = setLengthNode.first()
            setLengthNode.remove(top)

            for (edge in top.edges) {
                if(edge.hasExtension(RequirementExtension::class.java)) {
                    val req = edge.getExt<RequirementExtension>()
                    if(!req.hasRequirement())
                        continue
                }

                val b = edge.b
                var lengthToAdjNode = sqrt((top.tile.x - b.tile.x).toDouble().pow(2) + (top.tile.y - b.tile.y).toDouble().pow(2))
                if(top.tile.z != b.tile.z) {
                    lengthToAdjNode = 0.0
                }
                if((lengthToAdjNode + top.cost) < b.cost) {
                    b.cost = (lengthToAdjNode + top.cost)
                    b.parent = edge.a
                    setLengthNode.add(b)
                }
            }
        }

        var cur: WebVertex? = to
        while (cur != null) {
            path.add(0, cur)
            cur = cur.parent
        }

        return path
    }

    fun nextId() : Int {
        var id = 0
        while(vertices.containsKey(id)) {
            id++
        }
        return id
    }

    companion object {

        fun linkWalkEdge(v1: WebVertex, v2: WebVertex) {
            val e1 = WebEdge(v1, v2)
            v1.edges.add(e1)
            val e2 = WebEdge(v2, v1)
            v2.edges.add(e2)
        }

        fun linkMenuEdge(v1: WebVertex, v2: WebVertex, action: MenuAction, vararg args: Int) {
            if(args.size > 4)
                error("Too many menu args")
            val e1 = WebEdge(v1, v2, MenuEdge(action, *args))
            val e2 = WebEdge(v2, v1, MenuEdge(action, *args))
            v1.edges.add(e1)
            v2.edges.add(e2)
        }

        fun linkMenuEdgeTo(v1: WebVertex, v2: WebVertex, action: MenuAction, vararg args: Int) {
            if(args.size > 4)
                error("Too many menu args")
            val e1 = WebEdge(v1, v2, MenuEdge(action, *args))
            v1.edges.add(e1)
        }

        fun linkObjectEdgeTo(v1 : WebVertex, v2: WebVertex, action: ObjectAction, objectId: Int, tile: WorldTile) {
            val e1 = WebEdge(v1, v2, ObjectEdge(objectId, action, tile))
            v1.edges.add(e1)
        }

    }
}