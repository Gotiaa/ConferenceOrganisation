package domain.use_case

import common.Constants.CONFERENCE_END_DATE
import common.Constants.CONFERENCE_START_DATE
import common.utils.isBefore
import common.utils.toDateTransition
import common.utils.toLocalDateTime
import data.buildObjectFromJsonString
import data.findRightFile
import data.getJsonStringifyByPath
import domain.model.Aeroport
import domain.model.FlightType
import domain.model.dataFlight
import kotlin.random.Random

class FindFlight() {

    operator fun invoke(start : Aeroport, flightType: FlightType, voisinOf: dataFlight? = null) : dataFlight? {
        val paths = findRightFile(start, flightType)
        var tab : Array<dataFlight> = emptyArray()

        paths?.forEach { path ->
            val stringJson = getJsonStringifyByPath(path)
            val tableauConverti = stringJson!!.getFlightsArrayFromJsonStringify()
            tab += tableauConverti
        }
        var finalResult : dataFlight? = null
        if (voisinOf == null) {
            var isFlightValid = false
            var randomIndex : Int = 0
            while (!isFlightValid) {
                randomIndex = Random.nextInt(tab.size)
                isFlightValid = tab[randomIndex].isFlightValid(flightType)
            }
            finalResult = tab[randomIndex]
        }
        else {
            var index : Int? = null
            for ((i, value) in tab.distinct().withIndex()) {
                if (value.depart == voisinOf.depart && value.arrive == voisinOf.arrive){
                    index = i
                }

            }
            index.let { i ->
                if (i != null) {
                    if (tab[i+1].isFlightValid(flightType)) {
                        finalResult = tab[i+1]
                    } else if (tab[i-1].isFlightValid(flightType)) {
                        finalResult = tab[i-1]
                    } else finalResult = tab[i]
                }
            }
        }
        return finalResult
    }

    private fun dataFlight.isFlightValid(flightType: FlightType) : Boolean {
        val comparaisonDate = if (flightType == FlightType.ALLER ) CONFERENCE_START_DATE else CONFERENCE_END_DATE
        val isBefore = this.arrive?.toDateTransition()?.toLocalDateTime()?.isBefore(comparaisonDate.toDateTransition().toLocalDateTime())
        return isBefore ?: false
    }

    private fun String.getFlightsArrayFromJsonStringify(): Array<dataFlight> {
        return buildObjectFromJsonString(this)?.flights?.flight ?: emptyArray()
    }
}