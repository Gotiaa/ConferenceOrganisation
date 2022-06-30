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
import platform.posix.optional_argument
import kotlin.random.Random

class FindFlight() {

    operator fun invoke(start : Aeroport, flightType: FlightType) : dataFlight? {
        val paths = findRightFile(start, flightType)
        var tab : Array<dataFlight> = emptyArray()

        paths?.forEach { path ->
            val stringJson = getJsonStringifyByPath(path)
            val tableauConverti = stringJson!!.getFlightsArrayFromJsonStringify()
            tab += tableauConverti
        }

        var isFlightValid = false
        var randomIndex : Int = 0
        while (isFlightValid == false) {
            randomIndex = Random.nextInt(tab.size)
            isFlightValid = tab[randomIndex].isFlightValid(flightType)
        }
        val finalResult = tab[randomIndex]
        println("J'ai trouvé le vol suivant : $finalResult")
        return finalResult
    }

    fun dataFlight.isFlightValid(flightType: FlightType) : Boolean {
        val comparaisonDate = if (flightType == FlightType.ALLER ) CONFERENCE_START_DATE else CONFERENCE_END_DATE
        val isBefore = this.arrive?.toDateTransition()?.toLocalDateTime()?.isBefore(comparaisonDate.toDateTransition().toLocalDateTime())
        return isBefore ?: false
    }

    fun String.getFlightsArrayFromJsonStringify() : Array<dataFlight> {
        val result = buildObjectFromJsonString(this)?.flights?.flight ?: emptyArray()
        return result
    }
}