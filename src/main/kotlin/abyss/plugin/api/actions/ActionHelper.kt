package abyss.plugin.api.actions

import kraken.plugin.api.Actions

object ActionHelper {
    fun menu(action: ActionType, param1: Int, param2: Int, param3: Int) {
        Actions.menu(action.type, param1, param2, param3, 0)
    }
}