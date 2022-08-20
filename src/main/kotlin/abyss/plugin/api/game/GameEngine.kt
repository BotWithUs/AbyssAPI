package abyss.plugin.api.game

import abyss.plugin.api.variables.AbyssAPI

object GameEngine {

    @AbyssAPI
    external fun getEngineTime() : Long

}