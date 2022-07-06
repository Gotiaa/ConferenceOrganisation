package domain.use_case

import domain.model.Conferencier
import domain.model.FlightType
import findFlight
import kotlin.random.Random

class GetVoisinSolution() {

    operator fun invoke(conferenciers : List<Conferencier>) : List<Conferencier> {
        val conferencierMut = conferenciers.toMutableList()
        var randomIndex = 0
        randomIndex = Random.nextInt(conferenciers.size)
        val randomConferencier = conferenciers[randomIndex]
        conferencierMut[randomIndex] = randomConferencier.copy(
            volAller = findFlight.invoke(randomConferencier.aeroportDepart!!, FlightType.ALLER, randomConferencier.volAller)
        )
        return conferencierMut
    }
}