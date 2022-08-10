package abyss.plugin.api.plugin

import abyss.plugin.api.imgui.containers.ImPane
import kotlinx.coroutines.runBlocking
import kraken.plugin.api.ImGui
import kraken.plugin.api.Plugin

abstract class KotlinPlugin : Plugin() {



    override fun onLoop(): Int {
        return runBlocking {
            loop()
        }
    }

    abstract suspend fun loop(): Int

    abstract fun ImPane.createGui()

    final override fun initImGui() {
        super.initImGui()
        pane.createGui()
    }
}