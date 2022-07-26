package abyss.plugin.api.actions

enum class GroundItemAction(override val type: Int) : ActionType {
    GROUND_ITEM1(31), GROUND_ITEM2(32), GROUND_ITEM3(15), GROUND_ITEM4(33), GROUND_ITEM5(34), GROUND_ITEM6(35);

    override val actionIndex: Int
        get() = ordinal
}