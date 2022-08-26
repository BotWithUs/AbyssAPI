package abyss.plugin.api.actions.attributes

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

interface PluginAttributeSerializer {

    fun serialize(attributes: PluginAttributes) : ByteArrayOutputStream
    fun deserialize(attributes: PluginAttributes, inputStream: ByteArrayInputStream)

}