package abyss.plugin.api.world

import abyss.plugin.api.Vector3i

class WorldTile(x: Int, y: Int, z: Int) : Vector3i(x, y, z) {
    fun transform(x: Int, y: Int): WorldTile {
        val tile = WorldTile(this.x, this.y, z);
        tile.add(x, y, 0)
        return tile
    }

}

fun tile(x: Int, y: Int, z: Int = 0) = WorldTile(x, y, z)

fun Vector3i.expand(amount: Int) = expand(tile(amount, amount))