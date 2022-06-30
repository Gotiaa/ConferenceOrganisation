package domain.use_case

import domain.model.Aeroport
import okio.FileSystem
import okio.Path.Companion.toPath

class GetAeroports () {
    operator fun invoke() : List<Aeroport> {
        return getAeroport()
    }
}

private fun getAeroport(): List<Aeroport> {
    val path = "./src/nativeMain/resources/data/aeroports.txt"
    val aeroportFile = FileSystem.SYSTEM.read(path.toPath()) {
        readUtf8()
    }
    val aeroports = mutableListOf<Aeroport>()
    val lines = aeroportFile.split("\n")
    lines.forEach { line ->
        val dataLine = line.split("|")
        aeroports.add(
            Aeroport(
                code = dataLine.get(0),
                name = dataLine.get(1),
                city = dataLine.get(2),
                codeVille = dataLine.get(3),
                codePays = dataLine.get(4),
            )
        )
    }
    return aeroports
}
