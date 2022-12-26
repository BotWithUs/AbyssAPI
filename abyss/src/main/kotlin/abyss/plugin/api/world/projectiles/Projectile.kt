package abyss.plugin.api.world.projectiles

import abyss.plugin.api.Vector3
import abyss.plugin.api.Vector3i
import abyss.plugin.api.entities.Locatable

class Projectile : Locatable {

    private val id: Int = -1

    private val scenePosition: Vector3? = null
    private val globalPosition: Vector3i? = null

    fun getProjectileId() : Int {
        return id
    }

    fun getScenePosition() : Vector3? {
        return scenePosition
    }

    override fun getGlobalPosition(): Vector3i {
        if(globalPosition == null) {
            return Vector3i(0, 0, 0)
        }
        return globalPosition
    }


}