package abyss.plugin.api.imgui.fx

import abyss.plugin.api.imgui.button.ImButton
import javafx.beans.property.ReadOnlyProperty
import kotlin.reflect.KProperty

operator fun <T> ReadOnlyProperty<T>.getValue(imButton: ImButton, property: KProperty<*>): T {
    return value
}