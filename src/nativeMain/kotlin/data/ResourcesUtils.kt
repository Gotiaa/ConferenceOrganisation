package data

import domain.model.Aeroport
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath

const val resource_path = "./src/nativeMain/resources/data/07-26"
const val code_london_aeroport = "LHR"

fun findRightFile(startAeroport: Aeroport) : Path? {

    var fileFound = if (startAeroport.code != null) {
        val path = resource_path + "/" + startAeroport.code + "-" + code_london_aeroport + ".json"
        val searchFile = FileSystem.SYSTEM.exists(path.toPath())
        if (searchFile) path.toPath() else null
    } else null
    return fileFound

}