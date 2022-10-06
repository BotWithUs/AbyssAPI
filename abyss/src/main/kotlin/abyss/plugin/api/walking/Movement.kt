package abyss.plugin.api.walking

import abyss.plugin.api.coroutines.delayRandom
import abyss.plugin.api.coroutines.delayUntil
import abyss.plugin.api.world.WorldTile
import abyss.plugin.api.Move
import abyss.plugin.api.Players
import abyss.plugin.api.Vector3i
import abyss.plugin.api.world.WorldTile.Companion.tile
import kotlin.math.pow
import kotlin.math.sqrt

object Movement {

    suspend fun transverseTo(event: WalkEvent) : Boolean {
        if(event.web == null) {
            Move.to(event.dest)
            return true
        }
        val end = System.currentTimeMillis() + event.timeout
        val dest = event.dest
        val area = dest.expand(Vector3i(8, 8, 0))
        val ctx = TraverseContext()
        delayUntil {
            val self = Players.self() ?: return@delayUntil true
            return@delayUntil self.isMoving
        }
        var isFinished = false
        var failure = false
        while(!isFinished && System.currentTimeMillis() < end) {
            val self = Players.self()
            if(self == null) {
                isFinished = true
                failure = true
                continue
            }
            if(area.contains(self)) {
                isFinished = true
                continue
            }

            walkTo(dest, ctx, event)

            delayUntil {
                val p = Players.self() ?: return@delayUntil true
                return@delayUntil p.isMoving
            }

            delayRandom(300, 600)
        }

        return !failure
    }

    suspend fun walkTo(tile: WorldTile, ctx: TraverseContext, event: WalkEvent): MovementType {
        if(event.condition.met())
            return MovementType.TRAVERSE_BROKE
        if (ctx.isFinished)
            return MovementType.TRAVERSE_DONE
        val web = event.web ?: return MovementType.TRAVERSE_INVALID
        if (ctx.lastWalk != null && ctx.lastWalk!! == tile) {
            ctx.lastTile = WorldTile(0, 0, 0)
            ctx.lastWalk = WorldTile(0, 0, 0)
        }
        val self = Players.self() ?: return MovementType.TRAVERSE_INVALID
        val lastWalk = ctx.lastWalk

        val scenePos = self.scenePosition

        if (self.isMoving && lastWalk != null && lastWalk != tile(0, 0, 0)) {
            val psx = lastWalk.x * 512.0f + 256.0f
            val psy = lastWalk.y * 512.0f + 256.0f

            val distance = sqrt((scenePos.x - psx).pow(2) + (scenePos.y + psy).pow(2))
            if (distance > 5000.0) {
                return MovementType.TRAVERSE_MOVING
            }
        }

        ctx.lastTile = tile

        val begin = web.findClosest(self.globalPosition)
        val end = web.findClosest(tile)

        if (begin == null || end == null) {
            return MovementType.TRAVERSE_INVALID
        }

        var best: WebVertex? = null
        var edge: WebEdge? = null
        var bestDistance = 0.0
        var hasBest = false
        val path = web.findPath(begin, end)
        var canSkip = true
        var highestIndex = 0

        var i = 0
        while (i < path.size && canSkip) {
            val (id, p, v) = path[i]

            val psx = p.x * 512.0f + 256.0f
            val psy = p.y * 512.0f + 256.0f
            val distance = sqrt((scenePos.x - psx).pow(2) + (scenePos.y + psy).pow(2)).toDouble()
            var tmpEdge: WebEdge? = null
            if (i < (path.size - 1)) {
                tmpEdge = path[i].findEdge(path[i], path[i + 1])
                if (tmpEdge != null && !tmpEdge.canSkip(ctx)) {
                    canSkip = false
                }
            }

            if (distance < ctx.skipThreshold) {
                hasBest = true
                edge = tmpEdge
                bestDistance = distance
                best = v
                highestIndex = i
            }
            i++
        }

        val endOfPath = ((path.size - 1) == highestIndex)
        if (endOfPath && canSkip) {
            val psx = tile.x * 512.0f + 256.0f
            val psy = tile.y * 512.0f + 256.0f
            val distance = sqrt((scenePos.x - psx).pow(2) + (scenePos.y + psy).pow(2)).toDouble()
            if (distance < ctx.skipThreshold) {
                Move.to(tile)
                ctx.lastTile = tile(0, 0, 0)
                ctx.lastWalk = tile(0, 0, 0)
                return MovementType.TRAVERSE_DONE
            }
        }

        if (path.size == 1) {
            val (id, p, v) = path[0]
            val psx = p.x * 512.0f + 256.0f
            val psy = p.y * 512.0f + 256.0f
            val distance = sqrt((scenePos.x - psx).pow(2) + (scenePos.y + psy).pow(2)).toDouble()
            if (distance < 5000.0) {
                Move.to(tile)
                ctx.lastTile = tile(0, 0, 0)
                ctx.lastWalk = tile(0, 0, 0)
                return MovementType.TRAVERSE_DONE
            }
        }

        if (hasBest && best != null) {
            ctx.lastWalk = tile(best.tile.x, best.tile.y, best.tile.z)
            if (edge != null) {
                edge.transverse(bestDistance, best.tile, ctx)
            } else {
                Move.to(best.tile)
            }
            return MovementType.TRAVERSE_MOVING
        }
        ctx.lastTile = tile(0, 0, 0)
        ctx.lastWalk = tile(0, 0, 0)
        return MovementType.TRAVERSE_INVALID
    }



    enum class MovementType {
        TRAVERSE_DONE,
        TRAVERSE_BROKE,
        TRAVERSE_INVALID,
        TRAVERSE_MOVING
    }
}