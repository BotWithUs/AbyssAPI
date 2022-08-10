package abyss.plugin.api.game.actionbar

import abyss.plugin.api.actions.ActionHelper
import abyss.plugin.api.actions.MenuAction
import abyss.plugin.api.coroutines.delayUntil
import abyss.plugin.api.variables.Variables
import kotlinx.coroutines.runBlocking
import kraken.plugin.api.Widgets

object ActionBar {
    const val ACTION_WIDGET_ID = 1430
    const val NEXT_BAR_BUTTON_ID = 259
    const val PREV_BAR_BUTTON_ID = 258
    const val LOCK_BAR_BUTTON_ID = 262

    val barNumber get() = Variables.ACTION_BAR_NUMBER.value
    private val isLocked get() = Variables.ACTION_BAR_LOCKED.value

    @JvmStatic
    fun isLocked() = isLocked == 1

    @JvmStatic
    fun interact(slot: ActionSlot) {
        slot.interact()
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
            ActionHelper.menu(MenuAction.WIDGET, 1, -1, Widgets.hash(ACTION_WIDGET_ID, LOCK_BAR_BUTTON_ID))
            return delayUntil { isLocked() }
        }
        return true
    }

    suspend fun unlock() : Boolean {
        if(isLocked()) {
            ActionHelper.menu(MenuAction.WIDGET, 1, -1, Widgets.hash(ACTION_WIDGET_ID, LOCK_BAR_BUTTON_ID))
            return delayUntil { !isLocked() }
        }
        return false
    }

    suspend fun next() : Boolean {
        val current: Int by Variables.ACTION_BAR_NUMBER
        ActionHelper.menu(MenuAction.WIDGET, 1, -1, Widgets.hash(ACTION_WIDGET_ID, NEXT_BAR_BUTTON_ID))
        return delayUntil { barNumber == (current + 1) }
    }

    suspend fun previous() : Boolean {
        val current: Int by Variables.ACTION_BAR_NUMBER
        ActionHelper.menu(MenuAction.WIDGET, 1, -1, Widgets.hash(ACTION_WIDGET_ID, PREV_BAR_BUTTON_ID))
        return delayUntil { barNumber == (current - 1) }
    }
}