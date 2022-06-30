package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class dataFlight (
        val orig : String? = null,
        val price : Int? = null,
        val arrive : String? = null,
        val airline_display : String? = null,
        val stops : Int? = null,
        val dest : String? = null,
        val depart : String? = null
)

@Serializable
data class Flight(
        val flight : Array<dataFlight>? = null
)