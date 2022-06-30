package domain.use_case

import data.conferenciersVilles
import domain.model.Aeroport
import domain.model.Conferencier

class GetConferenciers() {

    operator fun invoke(aeroports : List<Aeroport>) : List<Conferencier> {
        val conferenciers = mutableListOf<Conferencier>()
        conferenciersVilles.forEach { ville ->
            aeroports.find { e -> e.city == ville }?.let { aeroport ->
                conferenciers.add(
                    Conferencier(
                        villeOrigine = ville,
                        aeroportDepart = aeroport
                    )
                )
            }
        }
        return conferenciers
    }
}