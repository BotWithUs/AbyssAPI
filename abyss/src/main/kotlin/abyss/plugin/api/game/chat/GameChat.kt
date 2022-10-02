package abyss.plugin.api.game.chat

import abyss.plugin.api.variables.AbyssAPI
import java.util.function.Predicate

object GameChat {
    @AbyssAPI
    external fun all() : Array<ChatMessage>

    @JvmStatic
    fun filter(predicate: Predicate<ChatMessage>) : List<ChatMessage> {
        val list = mutableListOf<ChatMessage>()
        for (msg in all()) {
            if(predicate.test(msg)) {
                list.add(msg)
            }
        }
        return list
    }

    @JvmStatic
    fun filterByName(name: String) : List<ChatMessage> {
        return filter { it.getName() == name }
    }

    @JvmStatic
    fun getPlayerMessages() : List<ChatMessage> {
        return filter {
            val name = it.getName()
            name == null || name.isEmpty()
        }
    }

}