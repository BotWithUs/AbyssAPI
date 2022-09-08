package abyss.plugin.api.actions.attributes

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

object PluginAttributeExtensions {

    fun PluginAttributes.setBoolean(key: String, value: Boolean) {
        put(key, value)
    }

    fun PluginAttributes.setString(key: String, value: String) {
        put(key, value)
    }

    fun PluginAttributes.getString(key: String) : String {
        return get(key) ?: ""
    }

    fun PluginAttributes.getBoolean(key: String) : Boolean {
        return try {
            get(key)?.toBoolean() ?: false
        } catch (e: Exception) {
            return false
        }
    }

    fun PluginAttributes.setInt(key: String, value: Int) {
        put(key, value)
    }

    fun PluginAttributes.getInt(key: String) : Int {
        return try {
            get(key)?.toInt() ?: 0
        } catch (_: Exception) {
            return 0
        }
    }

    fun PluginAttributes.setLong(key: String, value: Long) {
        put(key, value)
    }

    fun PluginAttributes.getLong(key: String) : Long {
        return try {
            get(key)?.toLong() ?: 0
        } catch (_: Exception) {
            return 0
        }
    }

    fun PluginAttributes.setDouble(key: String, value: Double) {
        put(key, value)
    }

    fun PluginAttributes.getDouble(key: String) : Double {
        return try {
            get(key)?.toDouble() ?: 0.0
        } catch (_: Exception) {
            return 0.0
        }
    }

    fun PluginAttributes.setFloat(key: String, value: Float) {
        put(key, value)
    }

    fun PluginAttributes.getFloat(key: String) : Float {
        return try {
            get(key)?.toFloat() ?: 0.0f
        } catch (_: Exception) {
            return 0.0f
        }
    }

    inline fun <reified T> PluginAttributes.setJson(key: String, value: T) {
        put(key, json.encodeToString(value))
    }

    inline fun <reified T> PluginAttributes.getJson(key: String) : T? {
        return try {
            val string = get(key) ?: return null
            json.decodeFromString<T>(string)
        } catch (_: Exception) {
            return null
        }
    }

}