package logica

import java.io.File

class Reader {
    open fun readCsv(fileName: String): List<List<String>> {
        val reader = File(fileName).bufferedReader()
        val header = reader.readLine()
        return reader.lineSequence()
            .filter { it.isNotBlank() }
            .map {
                it.split(',', ignoreCase = false)
            }.toList()
    }
}