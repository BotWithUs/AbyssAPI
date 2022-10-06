package com.abyss.definitions

import java.nio.ByteBuffer

interface Loader<T : Definition> {

    fun load(id: Int, buffer: ByteBuffer) : T
    fun newDefinition(id: Int): T

}