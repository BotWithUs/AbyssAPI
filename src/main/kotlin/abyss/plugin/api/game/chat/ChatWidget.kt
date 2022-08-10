package abyss.plugin.api.game.chat

import abyss.plugin.api.game.chat.ChatMessage.Companion.asChatMessage
import kraken.plugin.api.Widgets

enum class ChatWidget(val id: Int) {

    GLOBAL(137) {
        override fun getLines(): List<String> {
            val chat = Widgets.getGroupById(id)?.getWidget(0)
            if (chat != null) {
                val chatBox = chat
                    .getChild(3)
                    .getChild(2)
                    .getChild(1)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                return lines
            }
            return emptyList()
        }
    },
    CLAN_CHAT(1471) {
        override fun getLines(): List<String> {
            val widget = Widgets.getGroupById(id)?.getWidget(2)
            if (widget != null) {
                val chatBox = widget
                    .getChild(2)
                    .getChild(0)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                return lines
            }
            return emptyList()
        }
    },
    GROUP_CHAT(1529) {
        override fun getLines(): List<String> {
            val widget = Widgets.getGroupById(id)?.getWidget(2)
            if (widget != null) {
                val chatBox = widget
                    .getChild(2)
                    .getChild(0)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                return lines
            }
            return emptyList()
        }
    },
    PRIVATE_CHAT(1467) {
        override fun getLines(): List<String> {
            val widget = Widgets.getGroupById(id)?.getWidget(2)
            if (widget != null) {
                val chatBox = widget
                    .getChild(2)
                    .getChild(0)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                return lines
            }
            return emptyList()
        }
    },
    FRIENDS_CHAT(1472) {
        override fun getLines(): List<String> {
            val widget = Widgets.getGroupById(id)?.getWidget(2)
            if (widget != null) {
                val chatBox = widget
                    .getChild(2)
                    .getChild(0)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                return lines
            }
            return emptyList()
        }
    },
    GUEST_CLAN_CHAT(1470) {
        override fun getLines(): List<String> {
            val widget = Widgets.getGroupById(id)?.getWidget(2)
            if (widget != null) {
                val chatBox = widget
                    .getChild(2)
                    .getChild(0)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                return lines
            }
            return emptyList()
        }
    },
    TRADE_AND_ASSIT(464) {
        override fun getLines(): List<String> {
            val widget = Widgets.getGroupById(id)?.getWidget(2)
            if (widget != null) {
                val chatBox = widget
                    .getChild(2)
                    .getChild(0)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                return lines
            }
            return emptyList()
        }
    };

    abstract fun getLines(): List<String>

    fun getText(index: Int): String {
        val lines = getLines()
        if (lines.isEmpty()) return ""
        if (index < lines.size) {
            return lines[index]
        }
        return ""
    }

    operator fun component1() = getText(0).asChatMessage()
    operator fun component2() = getText(1).asChatMessage()
    operator fun component3() = getText(2).asChatMessage()
    operator fun component4() = getText(3).asChatMessage()
    operator fun component5() = getText(4).asChatMessage()
    operator fun get(index: Int, format: Boolean = true) = getText(index).asChatMessage(format)

    fun isOpen(): Boolean {
        return Widgets.isOpen(id)
    }

}