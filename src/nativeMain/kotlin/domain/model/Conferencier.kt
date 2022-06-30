package domain.model

data class Conferencier(
    val aeroportDepart: Aeroport? = null,
    val villeOrigine : String,
    val volAller : dataFlight? = null,
    val volRetour : dataFlight? = null
)