package abyss.plugin.api.actions.attributes

import abyss.plugin.api.Debug
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

    fun setBoolean(key: String, value: Boolean) = put(key, value)
    fun getBoolean(key: String): Boolean {
        if(!map.containsKey(key))
            return false
        val value = map[key] ?: return false
        return try {
            value.toBoolean()
        } catch (e: java.lang.Exception) {
            Debug.log("Failed to parse boolean attribute $key")
            false
        }
    }

    /*fun setBoolean(key: String, value: Boolean) {
        put(key, value)
    }

    fun setString(key: String, value: String) {
        put(key, value)
    }

    fun getString(key: String) : String {
        return get(key) ?: ""
    }

    fun getBoolean(key: String) : Boolean {
        return try {
            get(key)?.toBoolean() ?: false
        } catch (e: Exception) {
            Debug.log("Failed to load boolean attribut $key")
            return false
        }
    }

    fun setInt(key: String, value: Int) {
        put(key, value)
    }

    fun getInt(key: String) : Int {
        return try {
            get(key)?.toInt() ?: 0
        } catch (_: Exception) {
            Debug.log("Failed to load int attribut $key")
            return 0
        }
    }

    fun setLong(key: String, value: Long) {
        put(key, value)
    }

    fun getLong(key: String) : Long {
        return try {
            get(key)?.toLong() ?: 0
        } catch (_: Exception) {
            Debug.log("Failed to load long attribut $key")
            return 0
        }
    }

    fun setDouble(key: String, value: Double) {
        put(key, value)
    }

    fun getDouble(key: String) : Double {
        return try {
            get(key)?.toDouble() ?: 0.0
        } catch (_: Exception) {
            Debug.log("Failed to load double attribut $key")
            return 0.0
        }
    }

    fun setFloat(key: String, value: Float) {
        put(key, value)
    }

    fun getFloat(key: String) : Float {
        return try {
            get(key)?.toFloat() ?: 0.0f
        } catch (_: Exception) {
            Debug.log("Failed to load float attribut $key")
            return 0.0f
        }
    }*/

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