import common.calculateSolution
import common.isBetterThan
import common.toTotalCost
import domain.model.Conferencier
import domain.model.Cost
import domain.model.FlightType
import domain.model.TotalCost
import domain.use_case.*

val calculateCost = CalculateCost()
val findFlight = FindFlight()
val getAeroport = GetAeroports()
val getConferenciers = GetConferenciers()
val getVoisinSolution = GetVoisinSolution()

fun main() {

    println("Starting ...")

    val solutionSimple = getSimpleCombinaison()
    println(solutionSimple.calculateSolution().toString())

    val solutionHillClimbing = getCombinaisonByHillClimbing(solutionSimple)
    println("\nSolution par hill climbing :\n" + solutionHillClimbing.calculateSolution().toString())

    println("Ending ...")
}

fun getSimpleCombinaison() : List<Conferencier> {
    val conferenciers = getConferenciers.invoke(getAeroport.invoke())
    conferenciers.forEach { conferencier ->
        conferencier.volAller = findFlight.invoke(conferencier.aeroportDepart!!, FlightType.ALLER)
        conferencier.volRetour = findFlight.invoke(conferencier.aeroportDepart, FlightType.RETOUR)
    }
    return conferenciers
}



fun getCombinaisonByHillClimbing(solution : List<Conferencier>) : List<Conferencier> {
    val newSolution = getVoisinSolution(solution)
    if (newSolution.isBetterThan(solution)) return getCombinaisonByHillClimbing(newSolution)
    else return solution
}

fun getCombinaisonBySimulatedAnnealing() {

}