package common

import domain.model.Cost
import domain.model.TotalCost

fun List<Cost>.toTotalCost() : TotalCost {
    var sumPrixVolAller : Double = Double.MIN_VALUE
    var sumPrixVolRetour : Double = Double.MIN_VALUE
    var sumWaitCostAller : Double = Double.MIN_VALUE
    var sumWaitCostRetour : Double = Double.MIN_VALUE
    var sumTotalAller : Double = Double.MIN_VALUE
    var sumTotalRetour : Double = Double.MIN_VALUE
    var sumTotalAllerRetour : Double = Double.MIN_VALUE

    this.forEach { cost ->
        sumPrixVolAller += cost.prixVolAller
        sumPrixVolRetour += cost.prixVolRetour
        sumWaitCostAller += cost.waitCostAller
        sumWaitCostRetour += cost.waitCostRetour
        sumTotalAller += cost.totalAller
        sumTotalRetour += cost.totalRetour
        sumTotalAllerRetour += cost.totalAllerRetour
    }

    return TotalCost(
        prixVolAller = sumPrixVolAller,
        prixVolRetour = sumPrixVolRetour,
        waitCostAller = sumWaitCostAller,
        waitCostRetour = sumWaitCostRetour,
        totalAller = sumTotalAller,
        totalRetour = sumTotalRetour,
        totalAllerRetour = sumTotalAllerRetour
    )
}