package abyss.plugin.api.imgui.tabs

import abyss.plugin.api.imgui.ImNode
import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.containers.ImVerticalPane
import javafx.beans.property.SimpleStringProperty
import abyss.plugin.api.imgui.fx.getValue
import abyss.plugin.api.imgui.tabs.skins.ImTabSkin
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty

open class ImTab(title: String) : ImNode {

    val hiddenProperty = SimpleBooleanProperty(false)
    val hidden by hiddenProperty

    val titleProperty = SimpleStringProperty(title)
    val title by titleProperty

    val contentProperty = SimpleObjectProperty(ImVerticalPane())
    val content by contentProperty

    override fun getSkin(): ImSkin {
        return ImTabSkin(this)
    }
}