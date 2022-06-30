package domain.use_case

import common.Constants.CONFERENCE_END_DATE
import common.Constants.CONFERENCE_START_DATE
import common.Constants.MINUTE_COST
import common.utils.differenceTime
import common.utils.toDateTransition
import common.utils.toHours
import domain.model.FlightType
import domain.model.Flights
import domain.model.dataFlight

class CalculateCost {
    operator fun invoke(flight: dataFlight,flightType: FlightType, print : Boolean = false) : Double {
        val cost = calculateCost(flight, flightType)
        if (print) println("Prix du vol : ${flight.price}€ \nPrix de l'attente : $cost€ \nPrix total : ${flight.price?.plus(cost)}€")
        return cost + flight.price?.toDouble()!!
    }

    private fun calculateCost(flight: dataFlight, flightType: FlightType) : Double {
        return flight.timeCost(flightType)
    }
}

fun dataFlight.timeCost(flightType : FlightType) : Double {
    val waintingTime = if(flightType == FlightType.ALLER) {
        differenceTime(CONFERENCE_START_DATE.toDateTransition(), this.arrive!!.toDateTransition())
    } else {
        differenceTime(this.arrive!!.toDateTransition(), CONFERENCE_END_DATE.toDateTransition())
    }
    println("Temps d'attente : $waintingTime minutes (${waintingTime.toHours()}h)")
    return MINUTE_COST.toDouble() * waintingTime
}