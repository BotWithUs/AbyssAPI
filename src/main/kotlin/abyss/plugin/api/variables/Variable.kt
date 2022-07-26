package abyss.plugin.api.variables

sealed interface Variable {
    val variableId: Int
    val value: Int
}

class VariableBit(override val variableId: Int) : Variable {
    override val value: Int
        get() = VariableManager.getVarbitById(variableId)
}
class ConVarValue(override val variableId: Int) : Variable {
    override val value: Int
        get() = VariableManager.getConVarById(variableId)?.valueInt ?: 0
}

val Int.asVarbit: VariableBit get() = VariableBit(this)
val Int.asConVar: ConVarValue get() = ConVarValue(this)