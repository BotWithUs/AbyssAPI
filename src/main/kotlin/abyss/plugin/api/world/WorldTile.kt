package abyss.plugin.api.world

import kraken.plugin.api.Vector3i

class WorldTile(x: Int, y: Int, z: Int) : Vector3i(x, y, z)

fun tile(x: Int, y: Int, z: Int = 0) = WorldTile(x, y, z)

fun Vector3i.expand(amount: Int) = expand(tile(amount, amount))