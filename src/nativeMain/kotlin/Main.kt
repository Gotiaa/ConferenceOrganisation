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

    var totalCost : Double = Double.MIN_VALUE

    val conferenciers = getConferenciers.invoke(getAeroport.invoke())
    conferenciers.forEach {
        findFlight.invoke(it.aeroportDepart!!, FlightType.RETOUR)?.let { dataFlight ->
            totalCost += calculateCost.invoke(dataFlight, FlightType.RETOUR)
        }
        findFlight.invoke(it.aeroportDepart!!, FlightType.ALLER)?.let { dataFlight ->
            totalCost += calculateCost.invoke(dataFlight, FlightType.ALLER)
        }
    }

    println("Le voyage aller des conferenciers va couter : ${totalCost}€")
    println("Ending ...")
}