package abyss.plugin.extensions

fun IntRange.toArray() = toList().toIntArray()
fun LongRange.toArray() = toList().toLongArray()