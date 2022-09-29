package abyss.plugin.api.world

import abyss.plugin.api.Vector3i

class WorldTile(x: Int, y: Int, z: Int) : Vector3i(x, y, z) {

    val regionX: Int get() = x shr 6
    val regionY: Int get() = y shr 6
    val regionId: Int get() = (regionX shl 8) + regionY

    fun transform(x: Int, y: Int): WorldTile {
        val tile = WorldTile(this.x, this.y, z);
        tile.add(x, y, 0)
        return tile
    }

}

fun tile(x: Int, y: Int, z: Int = 0) = WorldTile(x, y, z)

fun Vector3i.expand(amount: Int) = expand(tile(amount, amount))