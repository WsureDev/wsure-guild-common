package top.wsure.guild.common.client

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocketListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.guild.common.utils.ScheduleUtils
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

abstract class WebsocketClient(
    private val wsUrl: String,
    retryWait:Long = 3000
) : WebSocketListener(),BotClient{
    private val needReconnect = AtomicBoolean(false)
    private val retryTask: suspend () -> Unit = suspend {
        if (needReconnect.get()) {
            doReconnect()
        }
    }
    var retryTimer: Timer = ScheduleUtils.loopEvent(retryTask,Date(),retryWait)

    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val wsClient: OkHttpClient = OkHttpClient.Builder().build()

    private val wsRequest: Request = Request.Builder()
        .get()
        .url(wsUrl)
        .build()

    private var connectWebSocket = wsClient.newWebSocket(wsRequest, listener()).also {
        logger.info("connect url :$wsUrl ")
    }

    override fun reconnect(){
        logger.trace("set needReconnect :true")
        needReconnect.set(true)
    }
    override fun connected(){
        logger.trace("set needReconnect :false")
        needReconnect.set(false)
    }

    override fun disconnect() {
        logger.trace("disconnect !")
        retryTimer.cancel()
        if(!connectWebSocket.close(1000,"reconnect")) connectWebSocket.cancel()
    }

    private fun doReconnect(){
        if(!connectWebSocket.close(1000,"reconnect")) connectWebSocket.cancel()
        logger.debug("do reconnect ... ")
        connectWebSocket = wsClient.newWebSocket(wsRequest,this)
    }

    override fun sendMessage(text: String){
        logger.debug("send text message $text")
        connectWebSocket.send(text)
    }

    private fun listener():WebSocketListener{
        return this
    }
}