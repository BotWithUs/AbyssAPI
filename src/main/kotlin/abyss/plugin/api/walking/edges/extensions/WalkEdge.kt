package abyss.plugin.api.walking.edges.extensions

import abyss.plugin.api.walking.TraverseContext
import abyss.plugin.api.walking.WebEdge
import abyss.plugin.api.walking.edges.WebEdgeExtension
import abyss.plugin.api.walking.WebVertex
import abyss.plugin.api.world.WorldTile
import kraken.plugin.api.Move

class WalkEdge : WebEdgeExtension {
    override suspend fun process(bestDistance: Double, edge: WebEdge, tile: WorldTile, ctx: TraverseContext): Boolean {
        Move.to(tile)
        return true
    }

    override suspend fun canSkip(edge: WebEdge, ctx: TraverseContext): Boolean {
        return false
    }
}