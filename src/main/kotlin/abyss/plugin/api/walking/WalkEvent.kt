package abyss.plugin.api.walking

import abyss.plugin.api.world.WorldTile
import kraken.plugin.api.Condition

class WalkEvent(val dest: WorldTile, val web: TraverseWeb? = null, val timeout: Long = 0) {

    var condition: Condition = Condition { false }
        private set

    fun setBreakCondition(condition: Condition) {
        this.condition = condition
    }

}