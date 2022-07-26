package abyss.plugin.api.actions

enum class PlayerAction(override val type: Int) : ActionType {
    PLAYER1(1), PLAYER2(2), PLAYER3(3), PLAYER4(4), PLAYER5(26), PLAYER6(27), PLAYER7(29), PLAYER8(30);

    override val actionIndex: Int
        get() = ordinal
}