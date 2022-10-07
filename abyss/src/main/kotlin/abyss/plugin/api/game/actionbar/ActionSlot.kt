package abyss.plugin.api.game.actionbar

import abyss.plugin.api.ConfigProvider
import abyss.plugin.api.actions.ActionHelper
import abyss.plugin.api.actions.MenuAction
import abyss.plugin.api.game.actionbar.shortcuts.*
import abyss.plugin.api.Inventory
import abyss.plugin.api.Item
import abyss.plugin.api.Widgets
import abyss.plugin.api.actions.ActionType

enum class ActionSlot(private val buttonId: Int, val type: Int, val id: Int) {

    SLOT_1(64, 1747, 1748),
    SLOT_2(77, 1749, 1750),
    SLOT_3(90, 1751, 1752),
    SLOT_4(103, 1753, 1754),
    SLOT_5(116, 1755, 1756),
    SLOT_6(129, 1757, 1758),
    SLOT_7(142, 1759, 1760),
    SLOT_8(155, 1761, 1762),
    SLOT_9(168, 1763, 1764),
    SLOT_10(181, 1765, 1766),
    SLOT_11(194, 1767, 1768),
    SLOT_12(207, 1769, 1770),
    SLOT_13(220, 1771, 1772),
    SLOT_14(233, 1773, 1774);

    fun getShortcut(): Shortcut {
        val shortcutType = ConfigProvider.getVarbitValue(type)
        val shortcutId = ConfigProvider.getVarbitValue(id)

        return Shortcut(ordinal, shortcutId, shortcutType).apply {
            when (this.type) {
                11 -> setExtension(TeleportShortcut(this.id))
                10 -> setExtension(ItemShortcut(
                    Item(
                        ConfigProvider.getVarpValue(
                            823 + slot
                        )
                    )
                ))
                else -> {
                    val ability = Abilities.fromId(this.type)
                    if(ability != null) {
                        setExtension(AbilityShortcut(ability, this.id))
                    }
                }
            }
        }
    }

    fun interact(action: ActionType) {
        ActionHelper.menu(action, 1, -1, Widgets.hash(ActionBar.ACTION_WIDGET_ID, buttonId))
    }

    fun drop() : Boolean {
        val shortcut = getShortcut()
        if(!shortcut.isItemShortcut()) return false
        val itemShortcut = shortcut.asItemShortcut() ?: return false
        if(!Inventory.contains { it.id == itemShortcut.item.id }) return false
        ActionHelper.menu(MenuAction.WIDGET, 8, -1, Widgets.hash(ActionBar.ACTION_WIDGET_ID, buttonId))
        return true
    }

    companion object {
        val values = values()
    }
}