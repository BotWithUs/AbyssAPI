package abyss.plugin.api.imgui.button

import abyss.plugin.api.imgui.AbstractImNode
import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.fx.getValue
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty

open class ImButton(text: String) : AbstractImNode() {

    val textProperty = SimpleStringProperty(text)
    val text by textProperty
    val onActionProperty = SimpleObjectProperty<(ImButton) -> Unit>()

    override fun getSkin(): ImSkin {
        return ImButtonSkin(this)
    }

    fun setOnAction(onAction : (ImButton) -> Unit) {
        onActionProperty.set(onAction)
    }
}
