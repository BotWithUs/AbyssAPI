package abyss.plugin.api.actions.attributes

import kotlinx.serialization.json.Json
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

object DefaultPluginAttributeSerializer : abyss.plugin.api.actions.attributes.PluginAttributeSerializer {

    override fun serialize(attributes: abyss.plugin.api.actions.attributes.PluginAttributes): ByteArrayOutputStream {
        val formatStream = ByteArrayOutputStream()
        val data = DataOutputStream(formatStream)
        data.writeShort(attributes.size())
        attributes.forEach { key, value ->
            data.writeUTF(key)
            data.writeUTF(value)
        }
        if(formatStream.size() > 2047) {
            error("File Too Large... Max Size is 2045 Bytes (2 kb)")
        }
        return formatStream
    }

    override fun deserialize(attributes: abyss.plugin.api.actions.attributes.PluginAttributes, inputStream: ByteArrayInputStream) {
        val data = DataInputStream(inputStream)
        val size = data.readUnsignedShort()
        repeat(size) {
            val key = data.readUTF()
            val value = data.readUTF()
            attributes.put(key, value)
        }
    }
}