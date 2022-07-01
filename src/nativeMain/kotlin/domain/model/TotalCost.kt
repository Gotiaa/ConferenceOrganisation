package domain.model

data class TotalCost (
        val prixVolAller : Double,
        val prixVolRetour : Double,
        val waitCostAller : Double,
        val waitCostRetour : Double,
        var totalAller : Double = Double.MIN_VALUE,
        var totalRetour : Double = Double.MIN_VALUE,
        var totalAllerRetour : Double = Double.MIN_VALUE,
) {
        override fun toString(): String {
                return "Prix total des billets aller : $prixVolAller$\n" +
                                "Prix total des billets retour : $prixVolRetour$\n" +
                                "Prix total de l'attente aller : $waitCostAller$\n" +
                                "Prix total de l'attente retour : $waitCostRetour$\n" +
                                "Prix total : $totalAllerRetour$"
        }
}