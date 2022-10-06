package abyss.plugin.api.test

import abyss.Utils
import abyss.map.Region
import abyss.map.WorldObject
import abyss.plugin.api.Cache
import abyss.plugin.api.Vector3i
import abyss.plugin.api.world.WorldTile
import abyss.plugin.api.world.WorldTile.Companion.tile
import com.abyss.definitions.ObjectType
import org.junit.jupiter.api.Test

class ClippingTest {

    @Test
    fun clippingTest() {
        val myPos = tile(3184, 3288, 0)

        val obj = WorldObject(3185, 3289, 0, 38732, ObjectType.SCENERY_INTERACT)

        //  -> Args= 0 3188 3290 1

        val pos = tile(3185, 3289, 0)
        val dist = Utils.getRouteDistanceTo(myPos, obj)


        val region = Region.get(obj.regionId)


        println(region.clipMap)
        println(dist)

        val fence = tile(3184, 3288, 0)
        val objs = region.objects[0][fence.xInRegion][fence.yInRegion]

        repeat(objs.size) {
            val o = objs[it]
            if (o != null) {
                println("Slot= $it - rotation= ${o.rotation} - Id= ${o.id} - clipType=${o.def.clipType} - access=${o.def.accessBlockFlag} - BlocksProj=${o.def.blocksProjectile} - types= ${o.type}")
            }
        }

    }

}