package abyss.plugin.api.plugin

import abyss.plugin.api.teleport.Lodestones
import abyss.plugin.api.variables.Variables
import kotlinx.coroutines.runBlocking
import kraken.plugin.api.Plugin

abstract class KotlinPlugin : Plugin() {
    
    override fun onLoop(): Int {
        return runBlocking {
            loop()
        }
    }

    abstract suspend fun loop(): Int
}