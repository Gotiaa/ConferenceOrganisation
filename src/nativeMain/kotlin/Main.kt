import common.toTotalCost
import domain.model.Cost
import domain.model.FlightType
import domain.use_case.CalculateCost
import domain.use_case.FindFlight
import domain.use_case.GetAeroports
import domain.use_case.GetConferenciers

fun main() {
    val calculateCost : CalculateCost = CalculateCost()
    val findFlight : FindFlight = FindFlight()
    val getAeroport : GetAeroports = GetAeroports()
    val getConferenciers : GetConferenciers = GetConferenciers()

    println("Starting ...")
    val costs = mutableListOf<Cost>()

    val conferenciers = getConferenciers.invoke(getAeroport.invoke())
    conferenciers.forEach { conferencier ->
        val volAller = findFlight.invoke(conferencier.aeroportDepart!!, FlightType.ALLER)
        val volRetour = findFlight.invoke(conferencier.aeroportDepart, FlightType.RETOUR)

        val cost = calculateCost.invoke(
            volAller = volAller!!,
            volRetour = volRetour!!,
        ).init()

        costs.add(cost)
    }

    val totalCost = costs.toTotalCost()
    println(totalCost.toString())
    println("Ending ...")
}