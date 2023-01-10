package abyss.plugin.api.widgets

import abyss.plugin.api.extensions.Extension

class EquipmentWidgetExtension(
    val cosmeticInventoryId: Int,
    val equipmentInventoryId: Int,
    val interfaceIndex: Int,
    val componentIndex: Int,
) : Extension