package abyss.plugin.api.extensions

import abyss.plugin.api.actions.attributes.PluginAttributes

interface Extension {

    fun save(attributes: PluginAttributes) {}
    fun load(attributes: PluginAttributes) {}

}