package org.example.de.aoc.twentyfive

import org.example.de.common.FileReader


fun solveDayFourPartOne(): Int {
    return 0
}

fun solveDayFourPartTwo(): Int {
    return 0
}

private fun getInput(): List<List<String>> {
    val input = FileReader.readFileFromResources("twentyfive/day_4_input.txt")
    return input.split("\n").map {
        it.split("")
    }.toList()
}