package abyss.plugin.api.imgui.input

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.label.ImLabel
import javafx.beans.property.SimpleStringProperty
import abyss.plugin.api.imgui.fx.getValue
import abyss.plugin.api.imgui.input.skins.ImTextFieldSkin
import javafx.beans.property.SimpleIntegerProperty

open class ImTextField(text: String, inputLength: Int = 50) : ImLabel(text) {

    val inputLengthProperty = SimpleIntegerProperty(inputLength)
    val inputLength by inputLengthProperty

    val inputProperty = SimpleStringProperty()
    val input by inputProperty

    override fun getSkin(): ImSkin {
        return ImTextFieldSkin(this)
    }
}