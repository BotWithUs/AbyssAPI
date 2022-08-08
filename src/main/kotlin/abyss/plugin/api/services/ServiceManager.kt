package abyss.plugin.api.services

import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

object ServiceManager {

    private val dispatcher = Executors.newScheduledThreadPool(1).asCoroutineDispatcher()

    fun registerService(service: GameService) {
        dispatcher.dispatch(dispatcher, service)
    }

}