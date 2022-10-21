package abyss.plugin.api.attributes

import abyss.plugin.api.plugin.attributes.Attributes
import kotlin.reflect.KProperty

sealed class AttributeDelegate(val attributes: Attributes)

class BooleanDelegate(attributes: Attributes, val value: Boolean) : AttributeDelegate(attributes) {
    operator fun getValue(ref: Any?, prop: KProperty<*>) : Boolean {
        if(!attributes.hasBoolean(prop.name)) {
            attributes.setBoolean(prop.name, value)
        }
        return attributes.getBoolean(prop.name)
    }
    operator fun setValue(ref: Any?, prop: KProperty<*>, value: Boolean) {
        attributes.setBoolean(prop.name, value)
    }
}

class IntDelegate(attributes: Attributes, val value: Int) : AttributeDelegate(attributes) {
    operator fun getValue(ref: Any?, prop: KProperty<*>) : Int {
        if(!attributes.hasInt(prop.name)) {
            attributes.setInt(prop.name, value)
        }
        return attributes.getInt(prop.name)
    }
    operator fun setValue(ref: Any?, prop: KProperty<*>, value: Int) {
        attributes.setInt(prop.name, value)
    }
}

class LongDelegate(attributes: Attributes, val value: Long) : AttributeDelegate(attributes) {
    operator fun getValue(ref: Any?, prop: KProperty<*>) : Long {
        if(!attributes.hasLong(prop.name)) {
            attributes.setLong(prop.name, value)
        }
        return attributes.getLong(prop.name)
    }
    operator fun setValue(ref: Any?, prop: KProperty<*>, value: Long) {
        attributes.setLong(prop.name, value)
    }
}

class FloatDelegate(attributes: Attributes, val value: Float) : AttributeDelegate(attributes) {
    operator fun getValue(ref: Any?, prop: KProperty<*>) : Float {
        if(!attributes.hasFloat(prop.name)) {
            attributes.setFloat(prop.name, value)
        }
        return attributes.getFloat(prop.name)
    }
    operator fun setValue(ref: Any?, prop: KProperty<*>, value: Float) {
        if(!attributes.hasFloat(prop.name)) {
            attributes.setFloat(prop.name, value)
        }
        attributes.setFloat(prop.name, value)
    }
}

class DoubleDelegate(attributes: Attributes, val value: Double) : AttributeDelegate(attributes) {
    operator fun getValue(ref: Any?, prop: KProperty<*>) : Double {
        return attributes.getDouble(prop.name)
    }
    operator fun setValue(ref: Any?, prop: KProperty<*>, value: Double) {
        attributes.setDouble(prop.name, value)
    }
}

class StringDelegate(attributes: Attributes, val value: String) : AttributeDelegate(attributes) {
    operator fun getValue(ref: Any?, prop: KProperty<*>) : String {
        return attributes.getString(prop.name)
    }
    operator fun setValue(ref: Any?, prop: KProperty<*>, value: String) {
        attributes.setString(prop.name, value)
    }
}