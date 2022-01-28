package top.wsure.guild.common.job

interface CornJob {
    suspend fun execute(params:Map<String,String> = mutableMapOf())
}