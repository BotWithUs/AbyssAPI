package abyss.plugin.api.world

import abyss.plugin.api.Move
import abyss.plugin.api.Vector3i

class WorldTile(x: Int, y: Int, z: Int) : Vector3i(x, y, z) {
    fun transform(x: Int, y: Int): WorldTile {
        val tile = WorldTile(this.x, this.y, z);
        tile.add(x, y, 0)
        return tile
    }

    fun interact(type: Int) = Move.to(this, type)

    operator fun component1() = regionId
    operator fun component2() = x
    operator fun component3() = y
    operator fun component4() = xInRegion
    operator fun component5() = yInRegion

    companion object {
        fun tile(x: Int, y: Int, z: Int = 0) = WorldTile(x, y, z)

        fun Vector3i.expand(amount: Int) = expand(tile(amount, amount))
    }
}

