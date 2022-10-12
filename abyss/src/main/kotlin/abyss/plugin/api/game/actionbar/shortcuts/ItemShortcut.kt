package abyss.plugin.api.game.actionbar.shortcuts

import abyss.plugin.api.ConfigProvider
import abyss.plugin.api.extensions.Extension
import abyss.plugin.api.Item

class ItemShortcut(val varpId: Int) : Extension {

    fun getItem() : Item {
        return Item(ConfigProvider.getVarpValue(varpId))
    }

}