package abyss.plugin.api.imgui.containers

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.containers.skins.ImHorizontalPaneSkin

open class ImHorizontalPane(id: String? = null) : ImVerticalPane(id) {

    override fun getSkin(): ImSkin {
        return ImHorizontalPaneSkin(this)
    }

}