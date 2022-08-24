package abyss.plugin.api.imgui.fx

import javafx.beans.property.ReadOnlyProperty
import javafx.beans.value.WritableObjectValue
import kotlin.reflect.KProperty

operator fun <T> ReadOnlyProperty<T>.getValue(imButton: Any?, property: KProperty<*>): T {
    return value
}

operator fun <T> WritableObjectValue<T>.setValue(imButton: Any?, property: KProperty<*>, value: T) {
    set(value)
}