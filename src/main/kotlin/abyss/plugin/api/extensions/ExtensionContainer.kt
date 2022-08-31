package abyss.plugin.api.extensions

interface ExtensionContainer<BASE : Extension> {

    fun getExt(clazz: Class<*>) : BASE

    fun hasExtension(clazz: Class<*>) : Boolean

    fun setExtension(extension: BASE)

}