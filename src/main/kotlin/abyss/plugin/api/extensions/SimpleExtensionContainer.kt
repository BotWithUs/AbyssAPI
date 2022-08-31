package abyss.plugin.api.extensions

open class SimpleExtensionContainer : ExtensionContainer<Extension> {

    private val extensions = mutableMapOf<Class<*>, Extension>()

    override fun getExt(clazz: Class<*>): Extension {
        return extensions.getOrDefault(clazz, NoOperationExtension)
    }

    override fun hasExtension(clazz: Class<*>): Boolean {
        return extensions.containsKey(clazz)
    }

    override fun setExtension(extension: Extension) {
        extensions[extension::class.java] = extension
    }
}