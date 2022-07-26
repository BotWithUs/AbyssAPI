package abyss.plugin.api.input

import abyss.plugin.api.coroutines.delayRandom
import kotlinx.coroutines.delay
import kraken.plugin.api.Input
import kraken.plugin.api.Rng
import java.util.function.Consumer

object InputHelper {

    suspend fun typeKey(key: Int, delay: Long = 1L) {
        pressKey(key)
        delay(delay)
        releaseKey(key)
    }

    suspend fun typeKey(literal: Char, delay: Long) {
        typeKey(literal.code, delay)
    }

    suspend fun typeCharacters(value: String, minDelay: Long = 10, maxDelay: Long = 30) {
        for (c in value.toCharArray()) {
            typeKey(c.code)
            delayRandom(minDelay.toInt(), maxDelay.toInt())
        }
    }

    @JvmStatic
    fun doKey(code: Int, consumer: Consumer<Void?>) {
        pressKey(code)
        consumer.accept(null)
        releaseKey(code)
    }

    @JvmStatic
    fun pressKey(code: Int) {
        Input.press(code)
    }

    @JvmStatic
    fun releaseKey(code: Int) {
        Input.release(code)
    }

    @JvmStatic
    fun typeKey(code: Int) {
        pressKey(code)
        releaseKey(code)
    }

    @JvmStatic
    fun typeCharLiteral(char: Char) {
        typeKey(char.code)
    }

    @JvmStatic
    fun moveMouse(x: Int, y: Int) {
        Input.moveMouse(x, y)
    }

    @JvmStatic
    fun clickMouse(button: Int) {
        Input.clickMouse(button)
    }

    @JvmStatic
    suspend fun enter(value: String, delayMin: Long = 10, delayMax: Long = 30) {
        val var5: CharArray = value.toCharArray()
        for (c in var5) {
            delay(Rng.i64(delayMin, delayMax))
            Input.key(c.code)
        }
    }



}