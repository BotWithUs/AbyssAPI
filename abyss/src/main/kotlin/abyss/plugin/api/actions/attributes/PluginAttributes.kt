package abyss.plugin.api.actions.attributes

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.function.BiConsumer
import kotlin.reflect.KProperty

class PluginAttributes(private val map: MutableMap<String, String>, private var serializer: PluginAttributeSerializer = DefaultPluginAttributeSerializer) : PluginAttributeSerializer {

    inline infix fun <reified T : Number> number(value: T) = AttributeDelegate(this, value, false)
    inline infix fun <reified T> json(value: T) = AttributeDelegate(this, value, true)

    fun boolean(default: Boolean) = AttributeDelegate(this, default, false)

    var json: Json = Json
        private set

    fun setJsonProvider(json: Json) {
        this.json = json
    }

    fun containsKey(key: String) = map.containsKey(key)

    fun size() = map.size

    fun getOrDefault(key: String, default: String) = map.getOrDefault(key, default)

    fun put(key: String, value: Any) {
        map[key] = value.toString()
    }

    fun get(key: String) = map[key]

    fun getValue(key: String) : String? {
        return map[key]
    }

    inline fun <reified T> get(key: String) : T? {
        return getValue(key) as T
    }

    fun forEach(consumer: BiConsumer<String, String>) = map.forEach { (t, u) ->
        consumer.accept(t, u)
    }

    fun getOrPut(key: String, supplier:  () -> String) : String {
        return map.getOrPut(key, supplier)
    }

    fun setSerializer(pluginAttributeSerializer: PluginAttributeSerializer) {
        serializer = pluginAttributeSerializer
    }

    class AttributeDelegate<T>(val attributes: PluginAttributes, val defaultValue: T, val json: Boolean) {
        inline operator fun <reified T> getValue(ref: Any?, prop: KProperty<*>) : T {
            val sprop = attributes.getOrPut(prop.name) { defaultValue.toString() }
            return try {
                if(json) {
                    return attributes.json.decodeFromString(sprop)
                }
                when(T::class) {
                    Int::class -> sprop.toInt()
                    Long::class -> sprop.toLong()
                    Boolean::class -> sprop.toBoolean()
                    Double::class -> sprop.toDouble()
                    Float::class -> sprop.toFloat()
                    UInt::class -> sprop.toUInt()
                    ULong::class -> sprop.toULong()
                    else -> sprop
                } as T
            } catch (e: Exception) {
                error("Can't get attribute ${prop.name}")
            }
        }

        inline operator fun <reified T> setValue(ref: Any?, prop: KProperty<*>, value: T) {
            try {
                if(json) {
                    attributes.getOrPut(prop.name) { attributes.json.encodeToString(value) }
                } else {
                    attributes.getOrPut(prop.name) { value.toString() }
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