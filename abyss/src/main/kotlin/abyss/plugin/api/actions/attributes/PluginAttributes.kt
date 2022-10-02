package abyss.plugin.api.actions.attributes

import javafx.beans.property.SimpleStringProperty
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.function.BiConsumer
import kotlin.reflect.KProperty

class PluginAttributes(private val map: MutableMap<String, SimpleStringProperty>, private var serializer: PluginAttributeSerializer = abyss.plugin.api.actions.attributes.DefaultPluginAttributeSerializer) : PluginAttributeSerializer {

    inline infix fun <reified T : Number> number(value: T) = AttributeDelegate(this, value, false)
    inline infix fun <reified T> json(value: T) = AttributeDelegate(this, value, true)

    fun boolean(default: Boolean) = AttributeDelegate(this, default, false)

    var json: Json = Json
        private set

    fun setJsonProvider(json: Json) {
        this.json = json
    }

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

    class AttributeDelegate<T>(val attributes: PluginAttributes, val defaultValue: T, val json: Boolean) {
        inline operator fun <reified T> getValue(ref: Any?, prop: KProperty<*>) : T {
            val sprop = attributes.getOrPut(prop.name) { SimpleStringProperty(defaultValue.toString()) }
            return try {
                if(json) {
                    return attributes.json.decodeFromString(sprop.value)
                }
                when(T::class) {
                    Int::class -> sprop.value.toInt()
                    Long::class -> sprop.value.toLong()
                    Boolean::class -> sprop.value.toBoolean()
                    Double::class -> sprop.value.toDouble()
                    Float::class -> sprop.value.toFloat()
                    UInt::class -> sprop.value.toUInt()
                    ULong::class -> sprop.value.toULong()
                    else -> sprop.value
                } as T
            } catch (e: Exception) {
                error("Can't get attribute ${prop.name}")
            }
        }

        inline operator fun <reified T> setValue(ref: Any?, prop: KProperty<*>, value: T) {
            try {
                if(json) {
                    attributes.getOrPut(prop.name) { SimpleStringProperty() }.set(attributes.json.encodeToString(value))
                } else {
                    attributes.getOrPut(prop.name) { SimpleStringProperty() }.set(value.toString())
                }
            } catch (_: Exception) {}
        }
    }

    override fun serialize(attributes: PluginAttributes): ByteArrayOutputStream {
        return serializer.serialize(attributes)
    }

    override fun deserialize(attributes: PluginAttributes, inputStream: ByteArrayInputStream) {
        serializer.deserialize(attributes, inputStream)
    }
}