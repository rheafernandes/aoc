package org.example.de.aoc.twentyfive

import org.example.de.common.FileReader

fun solveDayTwoPartOne(): Long {
    val productIdRanges = getInput()
    return productIdRanges.sumOf { idRange ->
        idRange.filter { !getValidProductId(it)}.sum()
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

private fun getValidProductId(productId: Long): Boolean {
    val productIdString = productId.toString()
    val productIdLen = productIdString.length
    if(productIdLen % 2 != 0)
        return true
    else {
        var isValid = false
        val middle = productIdLen / 2
        for(i in 0 ..< middle) {
            if(productIdString[i] != productIdString[i + middle]) {
                isValid = true
                break
            }
        }
        return isValid
    }
}
