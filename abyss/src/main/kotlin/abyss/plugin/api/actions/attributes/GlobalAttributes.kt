package abyss.plugin.api.actions.attributes

import java.io.*

object GlobalAttributes : abyss.plugin.api.actions.attributes.PluginAttributeSerializer {

    @JvmStatic
    val attributes = abyss.plugin.api.actions.attributes.PluginAttributes(HashMap(), this)

    @JvmStatic
    private val file = File("global_attributes.bin")

    @JvmStatic
    fun save() {
        if(!abyss.plugin.api.actions.attributes.GlobalAttributes.file.exists()) {
            abyss.plugin.api.actions.attributes.GlobalAttributes.file.createNewFile()
        }
        val out = abyss.plugin.api.actions.attributes.GlobalAttributes.attributes.serialize(abyss.plugin.api.actions.attributes.GlobalAttributes.attributes)
        abyss.plugin.api.actions.attributes.GlobalAttributes.file.writeBytes(out.toByteArray())
    }

    @JvmStatic
    fun load() {
        if(!abyss.plugin.api.actions.attributes.GlobalAttributes.file.exists()) return
        val inputStream = ByteArrayInputStream(abyss.plugin.api.actions.attributes.GlobalAttributes.file.readBytes())
        abyss.plugin.api.actions.attributes.GlobalAttributes.attributes.deserialize(abyss.plugin.api.actions.attributes.GlobalAttributes.attributes, inputStream)
    }

    override fun serialize(attributes: abyss.plugin.api.actions.attributes.PluginAttributes): ByteArrayOutputStream {
        val formatStream = ByteArrayOutputStream()
        val data = DataOutputStream(formatStream)
        data.writeShort(attributes.size())
        attributes.forEach { key, value ->
            data.writeUTF(key)
            data.writeUTF(value)
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