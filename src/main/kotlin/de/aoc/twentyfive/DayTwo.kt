package org.example.de.aoc.twentyfive

import org.example.de.common.FileReader

fun solveDayTwoPartOne(): Long {
    val productIdRanges = getInput()
    return productIdRanges.sumOf { idRange ->
        idRange.filter { !getValidProductId(it.toString(), 0, listOf(2)) }.sum()
    }
}

fun solveDayTwoPartTwo(): Long {
    val productIdRanges = getInput()
    return productIdRanges.sumOf { idRange ->
        idRange.filter { !getValidProductId(it.toString(), 0, listOf(2, 3, 5, 7)) }.sum()
    }
}

private fun getInput(): List<LongRange> {
    val input = FileReader.readFileFromResources("twentyfive/day_2_input.txt")
    val ranges = input.split(",")
    return ranges.map {
        val range = it.split("-")
        range[0].toLong()..range[1].toLong()
    }.toList()
}

private fun getValidProductId(productIdString: String, divisorIndex: Int, divisors: List<Int>): Boolean {
    val productIdLen = productIdString.length

    if (divisorIndex >= divisors.size)
        return true

    return if (productIdLen % divisors[divisorIndex] != 0)
        getValidProductId(productIdString, divisorIndex + 1, divisors)
    else {
        val sectionLen = productIdLen / divisors[divisorIndex]
        if(productIdString.chunked(sectionLen).distinct().size != 1) {
            getValidProductId(productIdString, divisorIndex + 1, divisors)
        } else {
            false
        }
    }
}