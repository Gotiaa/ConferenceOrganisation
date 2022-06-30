package domain.use_case

import common.Constants.CONFERENCE_START_DATE
import common.Constants.MINUTE_COST
import domain.model.dataFlight
import kotlinx.datetime.*

const val minuteInYear = 525600
const val minuteInMounth = 525600
const val minuteInDay = 1440
const val minuteInHour = 60

class CalculateCost() {
    operator fun invoke(flight: dataFlight) : Double {
        val cost = calculateCost(flight)
        println("Prix du vol : ${flight.price}€ \nPrix de l'attente : $cost€ \nPrix total : ${flight.price?.plus(cost)}€")
        return cost + flight.price?.toDouble()!!
    }

    private fun calculateCost(flight: dataFlight) : Double {
        return flight.timeCost()
    }
}

fun dataFlight.timeCost() : Double {
    val waintingTime = differenceTime(CONFERENCE_START_DATE.toDateTransition(), this.arrive!!.toDateTransition())
    println("Temps d'attente : $waintingTime minutes (${waintingTime.toHours()}h)")
    return MINUTE_COST.toDouble() * waintingTime
}

fun differenceTime(date1 : DateTransition, date2 : DateTransition) : Double {
    val localDateTime1 = LocalDateTime(date1.year, date1.mounth, date1.day, date1.hour, date1.minute, date1.second, 0)
    val localDateTime2 = LocalDateTime(date2.year, date2.mounth, date2.day, date2.hour, date2.minute, date2.second, 0)
    val differenceMin = localDateTime2.toInstant(TimeZone.UTC).until(localDateTime1.toInstant(TimeZone.UTC), DateTimeUnit.MINUTE)
    return differenceMin.toDouble()
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