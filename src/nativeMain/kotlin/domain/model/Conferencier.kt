package domain.model

data class Conferencier(
    val aeroportDepart: Aeroport? = null,
    val villeOrigine : String,
    var volAller : dataFlight? = null,
    var volRetour : dataFlight? = null
)