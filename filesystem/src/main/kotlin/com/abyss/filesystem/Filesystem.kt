package com.abyss.filesystem

import com.abyss.definitions.DefinitionManager
import com.abyss.definitions.ObjectDefinition
import com.abyss.definitions.ObjectLoader
import java.nio.ByteBuffer
import java.nio.file.Path

abstract class Filesystem(val path: Path) {

    private val checkedReferenceTables = BooleanArray(255)

    private val cachedReferenceTables = arrayOfNulls<ReferenceTable>(255)

    private val objectManager: DefinitionManager<ObjectDefinition> by lazy {
        DefinitionManager(this, 16, -1, 8, ObjectLoader())
    }

    abstract fun exists(index: Int, archive: Int): Boolean

    abstract fun read(index: Int, archive: Int): ByteBuffer?

    abstract fun read(index: Int, name: String): ByteBuffer?

    abstract fun readReferenceTable(index: Int): ByteBuffer?

    fun getReferenceTable(index: Int, ignoreChecked: Boolean = false): ReferenceTable? {
        val cached = cachedReferenceTables[index]
        if (cached != null) return cached

        if (!ignoreChecked) {
            if (checkedReferenceTables[index]) return null
            checkedReferenceTables[index] = true
        }

        val table = ReferenceTable(this, index)
        val container = readReferenceTable(index) ?: return null
        val data = ByteBuffer.wrap(Container.decode(container).data)
        table.decode(data)
        cachedReferenceTables[index] = table

        return table
    }

    abstract fun numIndices(): Int

    fun getObjectDefinition(id: Int) : ObjectDefinition {
        return objectManager[id]
    }
}