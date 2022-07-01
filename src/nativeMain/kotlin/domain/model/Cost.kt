package domain.model

data class Cost (
    val prixVolAller : Double,
    val prixVolRetour : Double,
    val waitCostAller : Double,
    val waitCostRetour : Double,
    var totalAller : Double = Double.MIN_VALUE,
    var totalRetour : Double = Double.MIN_VALUE,
    var totalAllerRetour : Double = Double.MIN_VALUE,

) {
    fun init() : Cost {
        this.totalAller = prixVolAller.plus(waitCostAller)
        this.totalRetour = prixVolRetour.plus(waitCostRetour)
        this.totalAllerRetour = totalAller.plus(totalRetour)

        return this
    }
}