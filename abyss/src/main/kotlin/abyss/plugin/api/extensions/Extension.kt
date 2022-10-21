package abyss.plugin.api.extensions

import javax.xml.stream.events.Attribute

interface Extension {

    fun save(attributes: Attribute) {}
    fun load(attributes: Attribute) {}

}