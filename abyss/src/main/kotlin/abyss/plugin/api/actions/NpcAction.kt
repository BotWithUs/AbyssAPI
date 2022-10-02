package abyss.plugin.api.actions

enum class NpcAction(override val type: Int) : abyss.plugin.api.actions.ActionType {
    NPC1(5), NPC2(6), NPC3(7), NPC4(8), NPC5(9), NPC6(10);

    override val actionIndex: Int
        get() = ordinal

    override val typeName: String
        get() = name

    companion object {
        fun forAction(index: Int): abyss.plugin.api.actions.NpcAction {
            for (value in values()) {
                if (value.actionIndex == index) {
                    return value
                }
            }
            return abyss.plugin.api.actions.NpcAction.NPC1
        }
    }
}