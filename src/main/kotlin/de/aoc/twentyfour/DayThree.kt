package org.example.de.aoc.twentyfour

import org.example.de.common.FileReader

fun solveDayThreePartOne():Int {
    val multiplyCommands =  getInput(FileReader.readFileFromResources("twentyfour/day_3_input.txt"))
    return multiplyCommands.sumOf { command -> solveMultiplication(command) }
}

fun solveDayThreePartTwo(): Int {
    val fileString = FileReader.readFileFromResources("twentyfour/day_3_input.txt")
    val multiplyCommands =  getInput(fileString).toMutableList()
    val dontDoSectionRegex = Regex("don't\\(\\)((?s).*?)(do\\(\\)|$)") //Got some help here from AI, I knew what to ask for.
    val dontDoCommands = dontDoSectionRegex.findAll(fileString).flatMap { matchResult ->
        val dontDoSection = matchResult.groups[0]?.value
        val commandsInDontDoSection = getInput(dontDoSection ?: "")
        commandsInDontDoSection
    }.toList()
    multiplyCommands.removeAll(dontDoCommands)
    return multiplyCommands.sumOf { command -> solveMultiplication(command) }
}

private fun solveMultiplication(command: String): Int {
    val digitsMatch = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
    val match = digitsMatch.find(command)
    match!!.destructured.let { (a, b) ->
        return a.toInt() * b.toInt()
    }
}

private fun getInput(input: String): List<String> {
    val multiplyCommandRegex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
    return multiplyCommandRegex.findAll(input).map { m -> m.value }.toList()
}