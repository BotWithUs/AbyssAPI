package abyss.plugin.api.teleport

interface Teleport {

    suspend fun teleport(): Boolean
    suspend fun isAvailable(): Boolean

}