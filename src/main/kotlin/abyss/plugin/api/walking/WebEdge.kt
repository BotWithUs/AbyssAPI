package abyss.plugin.api.walking

import abyss.plugin.api.extensions.Extension
import abyss.plugin.api.extensions.ExtensionContainer
import abyss.plugin.api.walking.edges.WebEdgeExtension
import abyss.plugin.api.walking.edges.extensions.WalkEdge
import abyss.plugin.api.world.WorldTile

class WebEdge(val a: WebVertex, val b: WebVertex, val ext: WebEdgeExtension = WalkEdge()) : ExtensionContainer<Extension> {

    private val extensions = mutableMapOf<Class<*>, Extension>()

    init {
        extensions[WebEdgeExtension::class.java] = ext
    }

    suspend fun transverse(bestDistance: Double, tile: WorldTile, ctx: TraverseContext): Boolean {
        return ext.process(bestDistance, this, tile, ctx)
    }

    suspend fun canSkip(ctx: TraverseContext): Boolean {
        return ext.canSkip(this, ctx)
    }

    override fun getExt(clazz: Class<*>): Extension {
        return extensions[clazz] ?: error("No such extension: ${clazz.simpleName}")
    }

    override fun hasExtension(clazz: Class<*>): Boolean {
        return extensions.containsKey(clazz)
    }

    override fun setExtension(extension: Extension) {
        if(extension is WebEdgeExtension) {
            extensions[WebEdgeExtension::class.java] = extension
        } else {
            extensions[extension::class.java] = extension
        }
    }
}