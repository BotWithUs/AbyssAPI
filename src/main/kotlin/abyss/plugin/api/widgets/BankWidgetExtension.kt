package abyss.plugin.api.widgets

import abyss.plugin.api.extensions.Extension

class BankWidgetExtension(
    val rootId: Int,
    val containerId: Int,
    val withdrawButtonId: Int,
    val depositButtonId: Int,
    val depositInventoryButtonId: Int,
    val depositEquipmentButtonId: Int,
    val toggleNotesButtonId: Int,
    val withdrawAsNoteConVarId: Int
) : Extension