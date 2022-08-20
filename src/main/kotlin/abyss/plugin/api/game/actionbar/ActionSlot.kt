package abyss.plugin.api.game.actionbar

import abyss.plugin.api.actions.ActionHelper
import abyss.plugin.api.actions.MenuAction
import kraken.plugin.api.Widgets

enum class ActionSlot(private val buttonId: Int) {

    SLOT_1(64),
    SLOT_2(77),
    SLOT_3(90),
    SLOT_4(103),
    SLOT_5(116),
    SLOT_6(129),
    SLOT_7(142),
    SLOT_8(155),
    SLOT_9(168),
    SLOT_10(181),
    SLOT_11(194),
    SLOT_12(207),
    SLOT_13(220),
    SLOT_14(233);

    fun interact(action: MenuAction) {
        ActionHelper.menu(action, 1, -1, Widgets.hash(ActionBar.ACTION_WIDGET_ID, buttonId))
    }

}