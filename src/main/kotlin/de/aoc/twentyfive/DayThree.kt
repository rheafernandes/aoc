package org.example.de.aoc.twentyfive

import org.example.de.common.FileReader

fun solveDayThreePartOne(): Int {
    val joltageBanks = getInput()

    return joltageBanks.sumOf { bank ->
        val requiredJoltageIndices = getNMaxIndices(bank.toMutableList(), 2)
        val joltage = requiredJoltageIndices.map { bank[it].toString() }.joinToString(separator = "").toInt()
        joltage
    }
}

fun solveDayThreePartTwo(): Int {
    return 0
}


private fun getNMaxIndices(bank: MutableList<Int>, n: Int): List<Int> {
    val indicesOfBatteries = bank.withIndex()
        .sortedByDescending { it.value }
        .take(n)
        .map { it.index }.toMutableList()
//    for (i in 0..<n) {
//        var max = Integer.MIN_VALUE
//        var tempIndex = -1
//        for (j in 0..<bank.size) {
//            if (bank[j] > max) {
//                max = bank[j]
//                tempIndex = j
//            }
//        }
//        indicesOfBatteries.add(tempIndex)
//    }
    indicesOfBatteries.sort()
    return indicesOfBatteries
}

private fun getInput(): List<List<Int>> {
    val input = FileReader.readFileFromResources("twentyfive/day_3_input.txt")
    val banks = input.split("\n")
    return banks.map { joltages ->
        joltages.map { it.digitToInt() }
    }.toList()
}