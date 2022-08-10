package abyss.plugin.api.game.chat

class ChatMessage(private val msg: String = "", private val format: Boolean = true) {

    val message: String get() {
        if(format) {
            return parseLine(msg)
        }
        return msg
    }

    private fun parseLine(line: String) : String {
        return try {
            line.replace(col, "").replace(time, "").trim()
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    companion object {
        val col = Regex("\\<.*?\\>")
        val time = Regex("\\[.*?\\]")

        fun String.asChatMessage(format: Boolean = true) = ChatMessage(this, format)
    }

}