package abyss.plugin.api.actions.attributes

import kotlin.reflect.KProperty

class PluginAttributes(private val map: MutableMap<String, String>) : MutableMap<String, String> by map {

    inline infix fun <reified T> default(value: T) = AttributeDelegate(this, value)

    class AttributeDelegate<T>(val attributes: PluginAttributes, val defaultValue: T) {
        inline operator fun <reified T> getValue(ref: Any?, prop: KProperty<*>) : T {
            return (attributes[prop.name] ?: defaultValue) as T
        }

        inline operator fun <reified T> setValue(ref: Any?, prop: KProperty<*>, value: T) {
            attributes[prop.name] = value.toString()
        }
    }
}