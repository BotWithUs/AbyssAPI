package abyss.plugin.api.game.actionbar

import abyss.plugin.api.actions.ActionHelper
import abyss.plugin.api.actions.MenuAction
import abyss.plugin.api.coroutines.delayUntil
import abyss.plugin.api.variables.Variables
import kotlinx.coroutines.runBlocking
import abyss.plugin.api.Widgets

object ActionBar {
    const val ACTION_WIDGET_ID = 1430
    const val NEXT_BAR_BUTTON_ID = 259
    const val PREV_BAR_BUTTON_ID = 258
    const val LOCK_BAR_BUTTON_ID = 262

    val barNumber get() = Variables.ACTION_BAR_NUMBER.value
    private val isLocked get() = Variables.ACTION_BAR_LOCKED.value

    @JvmStatic
    fun hasAbility(abilityId: Int) : Boolean {
        return ActionSlot.values
            .map { it.getShortcut() }
            .filter { it.isAbilityShortcut() }
            .mapNotNull { it.asAbilityShortcut() }
            .any { it.abilityId == abilityId }
    }
    @JvmStatic
    fun hasTeleport(teleportId: Int) : Boolean {
        return ActionSlot.values
            .map { it.getShortcut() }
            .filter { it.isTeleportShortcut() }
            .mapNotNull { it.asTeleportShortcut() }
            .any { it.teleportId == teleportId }
    }

    @JvmStatic
    fun hasItem(itemId: Int) : Boolean {
        return ActionSlot.values
            .map { it.getShortcut() }
            .filter { it.isItemShortcut() }
            .mapNotNull { it.asItemShortcut() }
            .any { it.item.id == itemId }
    }

    @JvmStatic
    fun forAbility(abilityId: Int) : ActionSlot? {
        if(!hasAbility(abilityId)) return null
        for (value in ActionSlot.values) {
            val shortcut = value.getShortcut()
            if(shortcut.isAbilityShortcut()) {
                val itemShortcut = shortcut.asAbilityShortcut()
                if(itemShortcut != null && itemShortcut.abilityId == abilityId) {
                    return value
                }
            }
        }
        return null
    }

    @JvmStatic
    fun forTeleport(teleportId: Int) : ActionSlot? {
        if(!hasTeleport(teleportId)) return null
        for (value in ActionSlot.values) {
            val shortcut = value.getShortcut()
            if(shortcut.isTeleportShortcut()) {
                val itemShortcut = shortcut.asTeleportShortcut()
                if(itemShortcut != null && itemShortcut.teleportId == teleportId) {
                    return value
                }
            }
        }
        return null
    }

    @JvmStatic
    fun forItem(itemId: Int) : ActionSlot? {
        if(!hasItem(itemId)) return null
        for (value in ActionSlot.values) {
            val shortcut = value.getShortcut()
            if(shortcut.isItemShortcut()) {
                val itemShortcut = shortcut.asItemShortcut()
                if(itemShortcut != null && itemShortcut.item.id == itemId) {
                    return value
                }
            }
        }
        return null
    }

    @JvmStatic
    fun isLocked() = isLocked == 1

    @JvmStatic
    fun interact(action: abyss.plugin.api.actions.MenuAction, slot: ActionSlot) {
        slot.interact(action)
    }

    @JvmStatic
    fun nextBar() {
        runBlocking { next() }
    }

    @JvmStatic
    fun prevBar() {
        runBlocking { previous() }
    }

    @JvmStatic
    fun lockBar() {
        runBlocking { lock() }
    }

    @JvmStatic
    fun unlockBar() {
        runBlocking { unlock() }
    }

    suspend fun lock() : Boolean {
        if(!isLocked()) {
            abyss.plugin.api.actions.ActionHelper.menu(abyss.plugin.api.actions.MenuAction.WIDGET, 1, -1, Widgets.hash(ACTION_WIDGET_ID, LOCK_BAR_BUTTON_ID))
            return delayUntil { isLocked() }
        }
        return true
    }

    suspend fun unlock() : Boolean {
        if(isLocked()) {
            abyss.plugin.api.actions.ActionHelper.menu(abyss.plugin.api.actions.MenuAction.WIDGET, 1, -1, Widgets.hash(ACTION_WIDGET_ID, LOCK_BAR_BUTTON_ID))
            return delayUntil { !isLocked() }
        }
        return false
    }

    suspend fun next() : Boolean {
        val current: Int by Variables.ACTION_BAR_NUMBER
        abyss.plugin.api.actions.ActionHelper.menu(abyss.plugin.api.actions.MenuAction.WIDGET, 1, -1, Widgets.hash(ACTION_WIDGET_ID, NEXT_BAR_BUTTON_ID))
        return delayUntil { barNumber == (current + 1) }
    }

    suspend fun previous() : Boolean {
        val current: Int by Variables.ACTION_BAR_NUMBER
        abyss.plugin.api.actions.ActionHelper.menu(abyss.plugin.api.actions.MenuAction.WIDGET, 1, -1, Widgets.hash(ACTION_WIDGET_ID, PREV_BAR_BUTTON_ID))
        return delayUntil { barNumber == (current - 1) }
    }
}