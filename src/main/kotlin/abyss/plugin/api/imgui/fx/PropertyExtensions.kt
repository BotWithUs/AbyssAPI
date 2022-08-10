package abyss.plugin.api.imgui.fx

import javafx.beans.property.ReadOnlyProperty
import kotlin.reflect.KProperty

operator fun <T> ReadOnlyProperty<T>.getValue(imButton: Any?, property: KProperty<*>): T {
    return value
}