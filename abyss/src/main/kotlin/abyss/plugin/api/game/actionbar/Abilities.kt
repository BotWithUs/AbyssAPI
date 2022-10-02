package abyss.plugin.api.game.actionbar

enum class Abilities(val id: Int) {

    MELEE(1),
    STRENGTH(2),
    DEFENCE(3),
    HEAL(4),
    RANGED(5),
    MAGIC(6),
    PRAYER(7);


    companion object {
        val values = values()
        fun fromId(id: Int) = values.find { it.id == id }
    }
}