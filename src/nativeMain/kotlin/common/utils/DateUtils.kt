package common.utils

import kotlinx.datetime.*


fun differenceTime(date1 : DateTransition, date2 : DateTransition) : Double {
    val localDateTime1 = date1.toLocalDateTime()
    val localDateTime2 = date2.toLocalDateTime()
    val differenceMin = localDateTime2.toInstant(TimeZone.UTC).until(localDateTime1.toInstant(TimeZone.UTC), DateTimeUnit.MINUTE)
    localDateTime1.toInstant(TimeZone.UTC)
    return differenceMin.toDouble()
}

fun DateTransition.toLocalDateTime() : LocalDateTime {
    return LocalDateTime(this.year, this.mounth, this.day, this.hour, this.minute, this.second, 0)
}

fun LocalDateTime.isBefore(localDateTime: LocalDateTime) : Boolean {
    val number1 = this.toInstant(TimeZone.UTC).epochSeconds
    val number2 = localDateTime.toInstant(TimeZone.UTC).epochSeconds
    return (number1-number2)>0
}

fun String.toDateTransition() : DateTransition {
    //2010-07-26T17:35:00
    val date = this.split("T").get(0)
    val hours = this.split("T").get(1)
    val result = DateTransition(
        year = date.split("-").get(0).toInt(),
        mounth = date.split("-").get(1).toInt(),
        day = date.split("-").get(2).toInt(),
        hour = hours.split(":").get(0).toInt(),
        minute = hours.split(":").get(1).toInt(),
        second = hours.split(":").get(2).toInt()
    )
    return result
}

data class DateTransition(
    val year : Int,
    val mounth : Int,
    val day : Int,
    val hour : Int,
    val minute: Int,
    val second : Int
) {}

fun Double.toHours() : Double {
    return this.div(60)
}