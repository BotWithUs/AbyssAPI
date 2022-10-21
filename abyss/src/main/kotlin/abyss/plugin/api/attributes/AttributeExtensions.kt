package abyss.plugin.api.attributes

import abyss.plugin.api.plugin.attributes.Attributes

fun Attributes.boolean(default: Boolean = false) = BooleanDelegate(this, default)
