package abyss.plugin.api.world

import kraken.plugin.api.Vector3
import kraken.plugin.api.Vector3i

class Projectile {

    private val id: Int = -1
    private val scenePosition: Vector3? = null
    private val globalPosition: Vector3i? = null

    fun getProjectileId() : Int {
        return id
    }

    fun getScenePosition() : Vector3? {
        return scenePosition
    }

    fun getGlobalPosition(): Vector3i? {
        return globalPosition
    }

}