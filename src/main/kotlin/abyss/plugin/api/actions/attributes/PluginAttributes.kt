package abyss.plugin.api.actions.attributes

import javafx.beans.property.SimpleStringProperty
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.function.BiConsumer
import kotlin.reflect.KProperty

class PluginAttributes(private val map: MutableMap<String, SimpleStringProperty>, private var serializer: PluginAttributeSerializer = DefaultPluginAttributeSerializer) : PluginAttributeSerializer {

    inline infix fun <reified T> default(value: T) = AttributeDelegate(this, value)

    fun addListener(key: String, consumer: BiConsumer<String, String>) {
        val prop = map[key] ?: return
        prop.addListener { _, oldValue, newValue ->
            consumer.accept(oldValue, newValue)
        }
    }

    fun containsKey(key: String) = map.containsKey(key)

    fun size() = map.size

    fun getOrDefault(key: String, default: String) = map.getOrDefault(key, SimpleStringProperty(default)).value

    fun put(key: String, value: Any) {
        map[key] = SimpleStringProperty(value.toString())
    }

    fun get(key: String) = map[key]?.value

    fun getValue(key: String) : Any? {
        return map[key]?.value
    }

    inline fun <reified T> get(key: String) : T? {
        return getValue(key) as T
    }

    fun forEach(consumer: BiConsumer<String, String>) = map.forEach { (t, u) ->
        consumer.accept(t, u.value)
    }

    fun getOrPut(key: String, supplier:  () -> SimpleStringProperty) : SimpleStringProperty {
        return map.getOrPut(key, supplier)
    }

    fun setSerializer(pluginAttributeSerializer: PluginAttributeSerializer) {
        serializer = pluginAttributeSerializer
    }

    class AttributeDelegate<T>(val attributes: PluginAttributes, val defaultValue: T) {
        inline operator fun <reified T> getValue(ref: Any?, prop: KProperty<*>) : T {
            val sprop = attributes.getOrPut(prop.name) { SimpleStringProperty(defaultValue.toString()) }
            return sprop.value as T
        }

        inline operator fun <reified T> setValue(ref: Any?, prop: KProperty<*>, value: T) {
            attributes.getOrPut(prop.name) { SimpleStringProperty() }.set(value.toString())
        }
    }

    override fun serialize(attributes: PluginAttributes): ByteArrayOutputStream {
        return serializer.serialize(attributes)
    }

    override fun deserialize(attributes: PluginAttributes, inputStream: ByteArrayInputStream) {
        serializer.deserialize(attributes, inputStream)
    }
}