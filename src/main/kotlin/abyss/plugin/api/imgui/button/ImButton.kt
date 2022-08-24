package abyss.plugin.api.imgui.button

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.label.ImLabel
import javafx.beans.property.SimpleObjectProperty

open class ImButton(text: String) : ImLabel(text) {

    val onActionProperty = SimpleObjectProperty<(ImButton) -> Unit>()


    override fun getSkin(): ImSkin {
        return ImButtonSkin(this)
    }

    fun setOnAction(onAction : (ImButton) -> Unit) {
        onActionProperty.set(onAction)
    }
}
