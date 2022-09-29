package abyss.plugin.api.actions

import abyss.plugin.api.Actions

object ActionHelper {
    fun menu(action: ActionType, param1: Int, param2: Int, param3: Int) {
        Actions.menu(action.type, param1, param2, param3, 0)
    }

    fun fromType(type: Int) : String? {
        for (value in MenuAction.values()) {
            if(value.type == type)
                return value.name
        }
        return null
    }
}