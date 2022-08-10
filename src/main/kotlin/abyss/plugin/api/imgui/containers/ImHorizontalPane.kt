package abyss.plugin.api.imgui.containers

import abyss.plugin.api.imgui.ImSkin
import abyss.plugin.api.imgui.containers.skins.ImHorizontalPaneSkin

open class ImHorizontalPane : ImVerticalPane() {

    override fun getSkin(): ImSkin {
        return ImHorizontalPaneSkin(this)
    }

}