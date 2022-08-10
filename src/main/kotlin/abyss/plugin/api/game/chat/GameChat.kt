package abyss.plugin.api.game.chat

import kraken.plugin.api.Widgets

object GameChat {

    private val chats = mutableMapOf<ChatType, ChatFactory>()

    init {

        chats[ChatType.GLOBAL] = ChatFactory {
            val chat = Widgets.getGroupById(137)?.getWidget(0)
            if (Widgets.isOpen(137) && chat != null) {
                val chatBox = chat
                    .getChild(3)
                    .getChild(2)
                    .getChild(1)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                lines
            } else emptyList()
        }

        chats[ChatType.CLAN] = ChatFactory {
            val widget = Widgets.getGroupById(1471)?.getWidget(2)
            if (Widgets.isOpen(1471) && widget != null) {
                val chatBox = widget
                    .getChild(2)
                    .getChild(0)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                lines
            } else emptyList()
        }

        chats[ChatType.GROUP] = ChatFactory {
            val widget = Widgets.getGroupById(1529)?.getWidget(2)
            if (Widgets.isOpen(1529) && widget != null) {
                val chatBox = widget
                    .getChild(2)
                    .getChild(0)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                lines
            } else emptyList()
        }

        chats[ChatType.PRIVATE] = ChatFactory {
            val widget = Widgets.getGroupById(1467)?.getWidget(2)
            if (Widgets.isOpen(1467) && widget != null) {
                val chatBox = widget
                    .getChild(2)
                    .getChild(0)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                lines
            } else emptyList()
        }

        chats[ChatType.FRIENDS] = ChatFactory {
            val widget = Widgets.getGroupById(1472)?.getWidget(2)
            if (Widgets.isOpen(1472) && widget != null) {
                val chatBox = widget
                    .getChild(2)
                    .getChild(0)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                lines
            } else emptyList()
        }

        chats[ChatType.GUEST_CLAN] = ChatFactory {
            val widget = Widgets.getGroupById(1470)?.getWidget(2)
            if (Widgets.isOpen(1470) && widget != null) {
                val chatBox = widget
                    .getChild(2)
                    .getChild(0)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                lines
            } else emptyList()
        }

        chats[ChatType.TRADE_AND_ASSIST] = ChatFactory {
            val widget = Widgets.getGroupById(464)?.getWidget(2)
            if (Widgets.isOpen(464) && widget != null) {
                val chatBox = widget
                    .getChild(2)
                    .getChild(0)
                val lineCount = chatBox.children.size
                val lines = mutableListOf<String>()
                repeat(lineCount) {
                    lines.add(chatBox.getChild(it).text)
                }
                lines
            } else emptyList()
        }

    }

    @JvmStatic
    fun getChatFactory(type: ChatType) = chats[type]!!

    @JvmStatic
    fun setFactory(type: ChatType, factory: ChatFactory) {
        chats[type] = factory
    }

    fun test() {

        val globalChat = GameChat.getChatFactory(ChatType.GLOBAL)
        val globalChat2 by ChatType.GLOBAL //Kotlin Support

        val firstMsg = globalChat.getChatMessage(0, true) //format removes <col=x> stuff

        //Kotlin Support
        val (msg1, msg2) = globalChat //kotlin deconstruction, gives ChatMessages
        val msg3 = globalChat[3, true] //Same as globalChat.getChatMessage(3, true)

        //If widget ids change you can set the factory
        GameChat.setFactory(ChatType.GLOBAL, ChatFactory {
            emptyList()
        })

    }

}