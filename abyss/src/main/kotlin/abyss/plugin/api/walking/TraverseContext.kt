package abyss.plugin.api.walking

import abyss.plugin.api.world.WorldTile

class TraverseContext {
    var skipThreshold = 50.0
    var lastTile: WorldTile? = null
    var lastWalk: WorldTile? = null
    var isFinished = false
}