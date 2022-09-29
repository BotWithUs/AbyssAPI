package abyss.plugin.api.plugin

import kotlinx.coroutines.runBlocking
import abyss.plugin.api.Player
import abyss.plugin.api.Plugin

abstract class KotlinPlugin : Plugin() {

    override fun onLoop(): Int {
        return runBlocking {
            loop()
        }
    }

    override fun onServerTick(self: Player, tickCount: Long): Int {
        return runBlocking { onTick(self, tickCount) }
    }

    abstract suspend fun loop(): Int

    abstract suspend fun onTick(self: Player, tickCount: Long): Int
}