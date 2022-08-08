package abyss.plugin.api.plugin

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