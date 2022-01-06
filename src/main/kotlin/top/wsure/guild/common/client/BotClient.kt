package top.wsure.guild.common.client

interface BotClient {

    fun reconnect()

    fun connected()

    fun disconnect()

    fun sendMessage(text: String)
}