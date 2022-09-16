package abyss.plugin.api.variables

import abyss.plugin.api.extensions.Extension
import abyss.plugin.api.variables.extensions.OreBoxExtension
import kraken.plugin.api.ConVar
import java.util.function.Supplier

object VariableManager {

    private val inventoryItemExtensions = mutableMapOf<Int, Supplier<Extension>>()

    init {
        OreBoxExtension.init()
    }

    @JvmStatic
    fun setExt(itemId: Int, supplier: Supplier<Extension>) {
        inventoryItemExtensions[itemId] = supplier
    }

    @JvmStatic
    fun getExt(itemId: Int) : Extension? {
        return inventoryItemExtensions[itemId]?.get()
    }

    @AbyssAPI
    external fun getContainerVarbit(containerId: Int, slot: Int, varbitId: Int) : Int

    @AbyssAPI
    external fun getVarbitById(varbitId: Int) : Int

    @AbyssAPI
    external fun getConVarById(convarId: Int) : ConVar?

}