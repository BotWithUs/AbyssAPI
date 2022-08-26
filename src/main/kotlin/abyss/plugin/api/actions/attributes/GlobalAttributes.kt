package abyss.plugin.api.actions.attributes

import java.io.ByteArrayInputStream
import java.io.File

object GlobalAttributes {

    @JvmStatic
    val attributes = PluginAttributes(HashMap())

    @JvmStatic
    private val file = File("global_attributes.bin")

    @JvmStatic
    fun save() {
        if(!file.exists()) {
            file.createNewFile()
        }
        val out = attributes.serialize(attributes)
        file.writeBytes(out.toByteArray())
    }

    @JvmStatic
    fun load() {
        if(!file.exists()) return
        val inputStream = ByteArrayInputStream(file.readBytes())
        attributes.deserialize(attributes, inputStream)
    }

}