package abyss.plugin.api.attributes

import abyss.plugin.api.plugin.attributes.Attributes

fun Attributes.boolean(default: Boolean = false) = BooleanDelegate(this, default)
fun Attributes.int(default: Int = 0) = IntDelegate(this, default)
fun Attributes.long(default: Long = 0) = LongDelegate(this, default)
fun Attributes.double(default: Double = 0.0) = DoubleDelegate(this, default)
fun Attributes.float(default: Float = 0.0f) = FloatDelegate(this, default)
fun Attributes.string(default: String = "") = StringDelegate(this, default)
