package abyss.plugin.api.extensions

inline fun <reified T : Extension> ExtensionContainer<*>.getExt() : T {
    return getExt(T::class.java) as T
}

inline fun <reified T : Extension> ExtensionContainer<*>.hasExt() : Boolean {
    return hasExtension(T::class.java)
}