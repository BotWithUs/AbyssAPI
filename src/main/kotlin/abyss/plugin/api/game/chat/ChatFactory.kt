package abyss.plugin.api.game.chat

import abyss.plugin.api.game.chat.ChatMessage.Companion.asChatMessage
import kraken.plugin.api.Widgets

fun interface ChatFactory {

    fun getLines(): List<String>

    fun getText(index: Int): String {
        val lines = getLines()
        if (lines.isEmpty()) return ""
        if (index < lines.size) {
            return lines[index]
        }
        return ""
    }

    fun getChatMessage(index: Int, format: Boolean = true) : ChatMessage {
        return ChatMessage(getText(index), format)
    }

    operator fun component1() = getText(0).asChatMessage()
    operator fun component2() = getText(1).asChatMessage()
    operator fun component3() = getText(2).asChatMessage()
    operator fun component4() = getText(3).asChatMessage()
    operator fun component5() = getText(4).asChatMessage()
    operator fun get(index: Int, format: Boolean = true) = getText(index).asChatMessage(format)
}