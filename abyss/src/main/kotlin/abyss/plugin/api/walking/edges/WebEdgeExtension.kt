package abyss.plugin.api.walking.edges

import abyss.plugin.api.extensions.Extension
import abyss.plugin.api.walking.TraverseContext
import abyss.plugin.api.walking.WebEdge
import abyss.plugin.api.walking.WebVertex
import abyss.plugin.api.world.WorldTile

interface WebEdgeExtension : Extension {
    suspend fun process(bestDistance: Double, edge: WebEdge, tile: WorldTile, ctx: TraverseContext): Boolean
    suspend fun canSkip(edge: WebEdge, ctx: TraverseContext): Boolean
}