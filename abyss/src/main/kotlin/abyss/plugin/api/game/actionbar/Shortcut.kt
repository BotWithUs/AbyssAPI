package abyss.plugin.api.game.actionbar

import abyss.plugin.api.extensions.NoOperationExtension
import abyss.plugin.api.extensions.SimpleExtensionContainer
import abyss.plugin.api.game.actionbar.shortcuts.AbilityShortcut
import abyss.plugin.api.game.actionbar.shortcuts.ItemShortcut
import abyss.plugin.api.game.actionbar.shortcuts.TeleportShortcut

class Shortcut(val slot: Int, val id: Int, val type: Int) : SimpleExtensionContainer() {

    fun isAbilityShortcut() = hasExtension(AbilityShortcut::class.java)
    fun isTeleportShortcut() = hasExtension(TeleportShortcut::class.java)
    fun isItemShortcut() = hasExtension(ItemShortcut::class.java)

    fun asAbilityShortcut() : AbilityShortcut? {
        val ext = getExt(AbilityShortcut::class.java)
        if(ext === NoOperationExtension) return null
        return ext as AbilityShortcut
    }

    fun asTeleportShortcut() : TeleportShortcut? {
        val ext = getExt(TeleportShortcut::class.java)
        if(ext === NoOperationExtension) return null
        return ext as TeleportShortcut
    }

    fun asItemShortcut() : ItemShortcut? {
        val ext = getExt(ItemShortcut::class.java)
        if(ext === NoOperationExtension) return null
        return ext as ItemShortcut
    }
}