package abyss.plugin.api.events

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

object EventDispatcher : CoroutineScope {
    override val coroutineContext: CoroutineContext = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    fun cancel(job: Job) {
        job.cancel()
    }
}

