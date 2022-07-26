package abyss.plugin.api.actions

enum class ObjectAction(override val type: Int) : ActionType {
    OBJECT1(13), OBJECT2(12), OBJECT3(19), OBJECT4(20), OBJECT5(21), OBJECT6(22);

    override val actionIndex: Int
        get() = ordinal

    companion object {
        fun forAction(index: Int): ObjectAction {
            for (value in values()) {
                if (value.actionIndex == index) {
                    return value
                }
            }
            return OBJECT1
        }
    }
}