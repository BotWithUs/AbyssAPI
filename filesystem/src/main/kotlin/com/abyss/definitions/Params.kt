package com.abyss.definitions

import com.abyss.filesystem.ext.getMedium
import com.abyss.filesystem.ext.string
import java.nio.ByteBuffer

class Params {
    var map: MutableMap<Int, Any>? = HashMap()
    fun parse(buffer: ByteBuffer) {
        val size: Int = buffer.get().toInt()
        repeat(size) {
            val bool = buffer.get().toInt() == 1
            val key: Int = buffer.getMedium()
            val value: Any = if (bool) buffer.string else buffer.int
            map!![key] = value
        }
    }

    operator fun get(id: Int): Any? {
        return map!![id]
    }

    fun getString(opcode: Int, defaultVal: String): String {
        if (map != null) {
            val value = map!![opcode]
            if (value != null && value is String) return value
        }
        return defaultVal
    }

    fun getString(opcode: Int): String {
        if (map != null) {
            val value = map!![opcode]
            if (value != null && value is String) return value
        }
        return "null"
    }

    fun getInt(opcode: Int, defaultVal: Int): Int {
        if (map != null) {
            val value = map!![opcode]
            if (value != null && value is Int) return value
        }
        return defaultVal
    }

    fun getInt(opcode: Int): Int {
        if (map != null) {
            val value = map!![opcode]
            if (value != null && value is Int) return value
        }
        return 0
    }
}