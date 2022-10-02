package com.abyss.utilities

fun <T : Any> Array<T?>.isJustNulls() : Boolean {
    return filterNotNull().none { it != "null" }
}