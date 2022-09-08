package abyss.plugin.api.walking.edges.extensions

import abyss.plugin.api.actions.ActionHelper
import abyss.plugin.api.actions.MenuAction
import abyss.plugin.api.walking.TraverseContext
import abyss.plugin.api.walking.WebEdge
import abyss.plugin.api.walking.edges.WebEdgeExtension
import abyss.plugin.api.walking.WebVertex
import abyss.plugin.api.world.WorldTile
import kraken.plugin.api.Move

class MenuEdge(val action: MenuAction, vararg val args: Int) : WebEdgeExtension {
    override suspend fun process(bestDistance: Double, edge: WebEdge, tile: WorldTile, ctx: TraverseContext): Boolean {
        if(args.size < 3 || edge.canSkip(ctx) || (bestDistance > 6000.0)) {
            Move.to(tile)
        } else {
            ActionHelper.menu(action, args[0], args[1], args[2])
        }
        return true
    }

    override suspend fun canSkip(edge: WebEdge, ctx: TraverseContext): Boolean {

        return false
    }
}