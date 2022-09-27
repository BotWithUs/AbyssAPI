package com.rshub.filesystem.ext

import com.rshub.utilities.TextUtils

fun String.toFilesystemHash(): Int {
    val size = length
    var char = 0
    for (index in 0 until size)
        char = (char shl 5) - char + TextUtils.charToCp1252(this[index])
    return char
}