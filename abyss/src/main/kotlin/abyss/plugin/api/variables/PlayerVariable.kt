package abyss.plugin.api.variables

import abyss.plugin.api.ConfigProvider

sealed interface PlayerVariable {
    val variableId: Int
    val value: Int
}

class VariableBit(override val variableId: Int) : PlayerVariable {
    override val value: Int
        get() = ConfigProvider.getVarbitValue(variableId)
}
class ConVarValue(override val variableId: Int) : PlayerVariable {
    override val value: Int
        get() = ConfigProvider.getVarpValue(variableId)
}

val Int.asVarbit: VariableBit get() = VariableBit(this)
val Int.asConVar: ConVarValue get() = ConVarValue(this)