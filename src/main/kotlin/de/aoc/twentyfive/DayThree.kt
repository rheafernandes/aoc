package org.example.de.aoc.twentyfive

import org.example.de.common.FileReader

fun solveDayThreePartOne(): Long {
    val joltageBanks = getInput()

    return joltageBanks.sumOf { bank ->
        val requiredJoltageIndices = getNBatteries(bank, 2)
        val joltage = requiredJoltageIndices.joinToString(separator = "") { bank[it].toString() }.toLong()
        joltage
    }
}

fun solveDayThreePartTwo(): Long {
    val joltageBanks = getInput()

    return joltageBanks.sumOf { bank ->
        val requiredJoltageIndices = getNBatteries(bank, 12)
        val joltage = requiredJoltageIndices.joinToString(separator = "") { bank[it].toString() }.toLong()
        joltage
    }
}

private fun getNBatteries(joltageBank: List<Long>, batteryCount: Int): List<Int> {
    var startIndex = 0
    var endIndex = joltageBank.size - (batteryCount - 1)
    val joltageIndices = mutableListOf<Int>()

    repeat(batteryCount) { i ->
        val indexFound = joltageBank.subList(startIndex, endIndex).withIndex().maxBy { it.value }.index
        joltageIndices.add(indexFound + startIndex)
        startIndex = indexFound + startIndex + 1
        endIndex = joltageBank.size - (batteryCount - 1 - (i + 1))
    }
    return joltageIndices.sorted()
}

private fun getInput(): List<List<Long>> {
    val input = FileReader.readFileFromResources("twentyfive/day_3_input.txt")
    val banks = input.split("\n")
    return banks.map { joltages ->
        joltages.map { it.digitToInt().toLong() }
    }.toList()
}