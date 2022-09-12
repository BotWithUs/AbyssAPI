package abyss.plugin.api.world

import abyss.plugin.api.variables.AbyssAPI

object LoginService {

    @AbyssAPI
    external fun getLoginStatus(): Int

    @AbyssAPI
    external fun hop(world: Int)

    @AbyssAPI
    external fun directLogin(username: String, password: String, world: Int)

}