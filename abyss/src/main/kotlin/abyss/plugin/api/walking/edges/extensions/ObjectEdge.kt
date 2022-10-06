package abyss.plugin.api.walking.edges.extensions

import abyss.plugin.api.actions.ActionHelper
import abyss.plugin.api.actions.ObjectAction
import abyss.plugin.api.walking.TraverseContext
import abyss.plugin.api.walking.WebEdge
import abyss.plugin.api.walking.edges.WebEdgeExtension
import abyss.plugin.api.world.WorldTile
import abyss.plugin.api.Move
import abyss.plugin.api.Players
import abyss.plugin.api.SceneObjects

class ObjectEdge(val objectId: Int, val action: abyss.plugin.api.actions.ObjectAction, val tile: WorldTile) : WebEdgeExtension {
    override suspend fun process(bestDistance: Double, edge: WebEdge, tile: WorldTile, ctx: TraverseContext): Boolean {

        if(edge.canSkip(ctx) || (bestDistance > 6000.0)) {
            Move.to(tile)
        } else {
            val obj = SceneObjects.closest { it.id == objectId && it.globalPosition == this.tile && !it.hidden() }

            if(obj != null) {
                ActionHelper.menu(action, obj.interactId, this.tile.x, this.tile.y)
                return true
            }
        }

        return false
    }

    override suspend fun canSkip(edge: WebEdge, ctx: TraverseContext): Boolean {
        val self = Players.self() ?: return false
        val distance = self.globalPosition.distance(tile)
        if (distance > 10) {
            return false
        }
        return SceneObjects.closest { it.id == objectId && it.globalPosition.x == tile.x && it.globalPosition.y == tile.y && it.globalPosition.z == tile.z && !it.hidden() } == null
    }
}