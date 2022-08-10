package abyss.plugin.api.imgui.checkbox

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.label.ImLabel
import javafx.beans.property.SimpleBooleanProperty
import abyss.plugin.api.imgui.fx.getValue


open class ImCheckbox(text: String, default: Boolean = false) : ImLabel(text) {

    val isCheckedProperty = SimpleBooleanProperty(default)
    val isChecked by isCheckedProperty

    override fun getSkin(): ImSkin {
        return ImCheckboxSkin(this)
    }
}