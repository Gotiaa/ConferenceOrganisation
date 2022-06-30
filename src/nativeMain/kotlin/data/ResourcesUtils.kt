package data

import domain.model.Aeroport
import domain.model.FlightType
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath

const val resource_path = "./src/nativeMain/resources/data/"
const val code_london_aeroport = "LHR"

val volsAller = listOf<String>("07-26", "07-27")
val volsRetour = listOf<String>("08-03", "08-04")
fun findRightFile(conferencierAeroport: Aeroport, flightType: FlightType) : List<Path>? {
    var filesToExplore = mutableListOf<String>()
    var startCode = ""
    var destinationCode = ""
    if (FlightType.ALLER == flightType) {
        filesToExplore = volsAller.toMutableList()
        startCode = conferencierAeroport.code!!
        destinationCode = code_london_aeroport
    } else {
        filesToExplore = volsRetour.toMutableList()
        startCode = code_london_aeroport
        destinationCode = conferencierAeroport.code!!
    }
    if (conferencierAeroport.code == null || filesToExplore.size == 0) {
        return null
    }

    val paths = mutableListOf<Path>()

    filesToExplore.forEach { folderName ->
        val path = resource_path + folderName + "/" + startCode + "-" + destinationCode + ".json"
        val searchFile = FileSystem.SYSTEM.exists(path.toPath())
        if (searchFile) paths.add(path.toPath())
    }
    return paths
}