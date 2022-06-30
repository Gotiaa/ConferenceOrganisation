package data

import domain.model.Flights
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okio.FileSystem
import okio.IOException
import okio.Path

fun getJsonStringifyByPath(path : Path): String? {
    try {
        val readmeContent = FileSystem.SYSTEM.read(path) {
            readUtf8()
        }
        return readmeContent
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
}

fun buildObjectFromJsonString(jsonString : String): Flights? {
    val flights = Json { ignoreUnknownKeys = true }.decodeFromString<Flights>(jsonString)
    return flights
}



