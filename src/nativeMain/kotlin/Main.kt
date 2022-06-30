import domain.model.Aeroport
import domain.model.dataFlight
import domain.use_case.CalculateCost
import domain.use_case.FindFlight
import domain.use_case.GetAeroports
import domain.use_case.GetConferenciers
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlin.math.tanh

fun main() {
    val calculateCost : CalculateCost = CalculateCost()
    val findFlight : FindFlight = FindFlight()
    val getAeroport : GetAeroports = GetAeroports()
    val getConferenciers : GetConferenciers = GetConferenciers()

    println("Starting ...")

    var totalCost : Double = Double.MIN_VALUE

    val conferenciers = getConferenciers.invoke(getAeroport.invoke())
    conferenciers.forEach {
        findFlight.invoke(it.aeroportDepart!!)?.let { dataFlight ->
            totalCost += calculateCost.invoke(dataFlight)
        }
    }

    println("Le voyage aller des conferenciers va couter : ${totalCost}€")
    println("Ending ...")
}