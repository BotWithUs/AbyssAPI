package abyss.plugin.api.teleport

interface Teleport {

    suspend fun teleport(): Boolean
    fun isAvailable(): Boolean

}