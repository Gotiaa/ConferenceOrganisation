package domain.use_case

import common.Constants.CONFERENCE_END_DATE
import common.Constants.CONFERENCE_START_DATE
import common.Constants.MINUTE_COST
import common.Constants.STOP_COST
import common.utils.differenceTime
import common.utils.toDateTransition
import domain.model.Cost
import domain.model.FlightType
import domain.model.dataFlight

class CalculateCost {
    operator fun invoke(volAller: dataFlight, volRetour : dataFlight, print : Boolean = false) : Cost {
        return Cost (
            prixVolAller = volAller.price!!.toDouble(),
            prixVolRetour = volRetour.price!!.toDouble(),
            waitCostAller = calculateCostOfWait(volAller, FlightType.ALLER),
            waitCostRetour = calculateCostOfWait(volRetour, FlightType.RETOUR),
        )
    }
    private fun calculateCostOfWait(flight: dataFlight, flightType: FlightType) : Double {
        return flight.timeCost(flightType)
    }
}

fun dataFlight.timeCost(flightType : FlightType) : Double {
    val waintingTime = if(flightType == FlightType.ALLER) {
        differenceTime(CONFERENCE_START_DATE.toDateTransition(), this.arrive!!.toDateTransition())
    } else {
        differenceTime(this.arrive!!.toDateTransition(), CONFERENCE_END_DATE.toDateTransition())
    }
    var cost = MINUTE_COST.toDouble() * waintingTime
    if (stops != null) cost += stops*STOP_COST
    return cost
}