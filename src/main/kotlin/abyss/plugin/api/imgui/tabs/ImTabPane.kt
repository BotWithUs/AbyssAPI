package abyss.plugin.api.imgui.tabs

import abyss.plugin.api.imgui.AbstractImNode
import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.tabs.skins.ImTabPaneSkin
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections

class ImTabPane : AbstractImNode() {

    val tabs = SimpleListProperty<ImTab>(FXCollections.observableArrayList())

    fun addTab(tab: ImTab) {
        tabs.add(tab)
    }

    fun removeTab(tab: ImTab) {
        tabs.remove(tab)
    }

    override fun getSkin(): ImSkin {
        return ImTabPaneSkin(this)
    }
}