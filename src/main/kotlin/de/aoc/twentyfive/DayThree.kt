package org.example.de.aoc.twentyfive

import org.example.de.common.FileReader

fun solveDayThreePartOne(): Long {
    val joltageBanks = getInput()

    return joltageBanks.sumOf { bank ->
        val requiredJoltageIndices = getNMaxIndices(bank.toMutableList(), 2)
        val joltage = requiredJoltageIndices.map { bank[it].toString() }.joinToString(separator = "").toLong()
        joltage
    }
}

fun solveDayThreePartTwo(): Long {
    val joltageBanks = getInput()

    return joltageBanks.sumOf { bank ->
        val requiredJoltageIndices = getNMaxIndices(bank.toMutableList(), 12)
        val joltage = requiredJoltageIndices.map { bank[it].toString() }.joinToString(separator = "").toLong()
        joltage
    }
}

private fun getNMaxIndices(bank: MutableList<Long>, n: Int): List<Int> {
    val indicesOfBatteries = mutableListOf<Int>()
    var tempIndex = -1
    for (i in 0..<n) {
        var max: Long = Long.MIN_VALUE
        val searchRange =
            if (tempIndex < bank.size - 1)
                (tempIndex + 1)..(bank.size - 1)
            else if (tempIndex > 0)
                0 until tempIndex
            else 0 until bank.size

        for (j in searchRange) {
            if (bank[j] > max) {
                max = bank[j]
                tempIndex = j
            }
        }

        indicesOfBatteries.add(tempIndex)
    }
    indicesOfBatteries.sort()
    return indicesOfBatteries
}

private fun getInput(): List<List<Long>> {
    val input = FileReader.readFileFromResources("twentyfive/day_3_input.txt")
    val banks = input.split("\n")
    return banks.map { joltages ->
        joltages.map { it.digitToInt().toLong() }
    }.toList()
}