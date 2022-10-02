package abyss.plugin.api.world.projectiles

import abyss.plugin.api.variables.AbyssAPI

object Projectiles {

    @AbyssAPI
    external fun all() : Array<Projectile>

}