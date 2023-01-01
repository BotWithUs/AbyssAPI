package abyss.plugin.api.game.chat

class ChatMessage {

    private val messageType: Int = 0
    private val rawName: String? = null
    private val name: String? = null
    private val message: String? = null
    private val timestamp: Long = 0

    fun getMessageType() = messageType

    fun isMessage(type: MessageType) : Boolean {
        return messageType in type.types
    }

    fun getRawMessage() = message
    fun getMessage() : String? {
        if(message == null) return null
        return parseLine(message)
    }

    fun getName() = name

    fun getTimestamp() = timestamp

    fun getRawName() = name
    fun getFullName(): String? {
        if(rawName == null) return null
        return parseLine(rawName.replace("?", " "))
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
    }
}