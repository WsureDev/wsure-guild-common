package top.wsure.guild.common.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object TimeUtils {
    val DAY_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")


    fun todayString(): String {
        return LocalDateTime.now().format(DAY_FORMATTER)
    }

    fun Long.toLocalDateTime():LocalDateTime{
        val instant = if(this.toString().length > 10) Instant.ofEpochMilli(this)
        else Instant.ofEpochSecond(this)
        return  LocalDateTime.ofInstant(
            instant,
            TimeZone.getDefault().toZoneId()
        )
    }
    fun LocalDateTime.toEpochMilli():Long{
        return  this.toInstant(OffsetDateTime.now().offset).toEpochMilli()
    }
    fun LocalDateTime.toEpochSecond():Long{
        return  this.toEpochSecond(OffsetDateTime.now().offset)
    }

}