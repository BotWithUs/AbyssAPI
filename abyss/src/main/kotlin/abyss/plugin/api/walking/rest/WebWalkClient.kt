package abyss.plugin.api.walking.rest

import abyss.plugin.api.walking.WebVertex
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class WebWalkClient {

    val client = HttpClient(CIO)

    fun addVertex(web: WebVertex) = runBlocking {
        val t = client.post("localhost:8080/game/web")
        val j = Json { prettyPrint = true }



    }

}