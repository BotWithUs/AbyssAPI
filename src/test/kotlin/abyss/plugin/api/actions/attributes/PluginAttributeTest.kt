package abyss.plugin.api.actions.attributes

import abyss.plugin.api.actions.attributes.PluginAttributeExtensions.getBoolean
import abyss.plugin.api.actions.attributes.PluginAttributeExtensions.getDouble
import abyss.plugin.api.actions.attributes.PluginAttributeExtensions.getFloat
import abyss.plugin.api.actions.attributes.PluginAttributeExtensions.getInt
import abyss.plugin.api.actions.attributes.PluginAttributeExtensions.getJson
import abyss.plugin.api.actions.attributes.PluginAttributeExtensions.getLong
import abyss.plugin.api.actions.attributes.PluginAttributeExtensions.setDouble
import abyss.plugin.api.actions.attributes.PluginAttributeExtensions.setFloat
import abyss.plugin.api.actions.attributes.PluginAttributeExtensions.setInt
import abyss.plugin.api.actions.attributes.PluginAttributeExtensions.setJson
import abyss.plugin.api.actions.attributes.PluginAttributeExtensions.setLong
import org.junit.jupiter.api.Test

class PluginAttributeTest {

    @Test
    fun pluginAttributeTest() {
        val attr = PluginAttributes(HashMap())

        @kotlinx.serialization.Serializable
        data class Person(val name: String, val age: Int)

        attr.setJson("person", Person("mash", 23))

        val person = attr.getJson<Person>("person")

        assert(person != null)
        assert(person!!.name == "mash")
        assert(person.age == 23)

        attr.setInt("int_value", 55)

        assert(attr.containsKey("int_value"))
        assert(attr.getInt("int_value") == 55)

        attr.setLong("long_value", 56)

        assert(attr.containsKey("long_value"))
        assert(attr.getLong("long_value") == 56L)

        attr.setDouble("double_value", 56.5)

        assert(attr.containsKey("double_value"))
        assert(attr.getDouble("double_value") == 56.5)

        attr.setFloat("float_value", 33.3f)

        assert(attr.containsKey("float_value"))
        assert(attr.getFloat("float_value") == 33.3f)

        var vl: Int by attr.number(0)

        vl = 5

        assert(attr.containsKey("vl"))
        assert(vl == 5)

        var t: Boolean by attr.boolean(false)

        t = true

        assert(attr.containsKey("t"))
        assert(t)
        assert(attr.getBoolean("t"))

        val p1 = Person("john", 33)

        var john: Person by attr.json(p1)

        john = Person("bill", 38)

        assert(attr.containsKey("john"))
        assert(john.name == "bill")
        assert(john.age == 38)
    }

}