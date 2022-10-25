package abyss.plugin.api.queries

import abyss.plugin.api.query.Queries
import abyss.plugin.api.query.npc.NpcQuery

class NpcQueryDSL(val query: NpcQuery = Queries.newNpcQuery()) {
    fun names(nameBuilder: NameBuilder.() -> Unit) {
        val nb = NameBuilder()
        nameBuilder(nb)
        query.names(*nb.names.toTypedArray())
    }

    inner class NameBuilder {
        val names = mutableListOf<String>()
        operator fun String.unaryPlus() {
            names.add(this)
        }
    }

    private fun test() {

    }

}

