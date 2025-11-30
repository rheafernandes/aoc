package org.example.de.common

object FileReader {
    fun readFileFromResources(fileName: String): String {
        val inputStream = Thread.currentThread().contextClassLoader.getResourceAsStream(fileName)
            ?: throw IllegalArgumentException("File not found: $fileName")
        return inputStream.bufferedReader().use { it.readText() }
    }
}