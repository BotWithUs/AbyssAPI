package abyss.plugin.api.variables

import kraken.plugin.api.ConVar

object VariableManager {

    @AbyssAPI
    external fun getVarbitById(varbitId: Int) : Int

    @AbyssAPI
    external fun getConVarById(convarId: Int) : ConVar?

}