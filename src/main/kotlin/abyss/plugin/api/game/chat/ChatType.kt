package abyss.plugin.api.game.chat

import kotlin.reflect.KProperty

enum class ChatType {

    GLOBAL,
    CLAN,
    GUEST_CLAN,
    GROUP,
    PRIVATE,
    FRIENDS,
    TRADE_AND_ASSIST;

    operator fun getValue(ref: Any?, prop: KProperty<*>) : ChatFactory {
        return GameChat.getChatFactory(this)
    }
}