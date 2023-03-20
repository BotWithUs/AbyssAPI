package abyss.plugin.api.plugin

import abyss.plugin.api.entities.Player
import abyss.plugin.api.Plugin
import kotlinx.coroutines.runBlocking

abstract class KotlinPlugin : Plugin() {
    override fun onLoop(): Int {
        return runBlocking {
            loop()
        }
    }

    override fun onServerTick(tickCount: Long): Int {
        return runBlocking { onTick(tickCount) }
    }

    abstract suspend fun loop(): Int

    abstract suspend fun onTick(tickCount: Long): Int
}