package abyss.plugin.api.actions.attributes

import java.io.*

object GlobalAttributes : PluginAttributeSerializer {

    @JvmStatic
    val attributes = PluginAttributes(HashMap(), this)

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

    override fun serialize(attributes: PluginAttributes): ByteArrayOutputStream {
        val formatStream = ByteArrayOutputStream()
        val data = DataOutputStream(formatStream)
        data.writeShort(attributes.size())
        attributes.forEach { key, value ->
            data.writeUTF(key)
            data.writeUTF(value)
        }

        return formatStream
    }

    override fun deserialize(attributes: PluginAttributes, inputStream: ByteArrayInputStream) {
        val data = DataInputStream(inputStream)
        val size = data.readUnsignedShort()
        repeat(size) {
            val key = data.readUTF()
            val value = data.readUTF()
            attributes.put(key, value)
        }
    }

}