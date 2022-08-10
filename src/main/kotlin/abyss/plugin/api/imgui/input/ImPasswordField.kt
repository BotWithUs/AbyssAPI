package abyss.plugin.api.imgui.input

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.input.skins.ImPasswordFieldSkin

class ImPasswordField(text: String, inputLength: Int = 50) : ImTextField(text, inputLength) {
    override fun getSkin(): ImSkin {
        return ImPasswordFieldSkin(this)
    }
}