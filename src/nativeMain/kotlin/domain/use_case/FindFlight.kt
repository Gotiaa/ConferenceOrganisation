package domain.use_case

import data.buildObjectFromJsonString
import data.findRightFile
import data.getJsonStringifyByPath
import domain.model.Aeroport
import domain.model.dataFlight
import kotlin.random.Random

class FindFlight() {

    operator fun invoke(start : Aeroport) : dataFlight? {
        val findJsonFile = findRightFile(start)
        val result = findJsonFile?.let { file ->
            val stringJson = getJsonStringifyByPath(file)
            stringJson?.let { str ->
                val flights = buildObjectFromJsonString(str)
                flights?.let { flight ->
                    flight.flights.flight?.let { dataFlights ->
                        val randomIndex = Random.nextInt(dataFlights.size);
                        dataFlights[randomIndex]
                    }
                }
            }
        }
        return result
    }
}