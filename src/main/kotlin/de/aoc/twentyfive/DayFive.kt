package org.example.de.aoc.twentyfive

import org.example.de.common.FileReader

fun solveDayFivePartOne(): Int {
    val (freshItemRanges, availableItems) = getInput()
    val sortedFreshItemRanges = freshItemRanges.sortedBy { it.first }
    return availableItems.count { item ->
        sortedFreshItemRanges.any { findInRange(it.first, it.last, item) }
    }
}

fun solveDayFivePartTwo(): Long {
    val (freshItemRanges, _) = getInput()
    val sortedFreshItemRanges = freshItemRanges.sortedBy { it.first }
    var start: Long = sortedFreshItemRanges.first().first
    var end: Long = sortedFreshItemRanges.first().last
    var totalCount = end - start + 1
    sortedFreshItemRanges.drop(1).forEachIndexed { index, range ->
        if (end >= range.first && end >= range.last) {
            totalCount += 0
        } else if (end >= range.first) {
            start = end + 1
            end = range.last
            totalCount += (end - start + 1)
        } else {
            start = range.first
            end = range.last
            totalCount += (end - start + 1)
        }
    }
    return totalCount
}

private fun findInRange(start: Long, end: Long, searchNumber: Long): Boolean {
    return searchNumber <= end && searchNumber >= start
}

private fun getInput(): Pair<List<LongRange>, List<Long>> {
    val input = FileReader.readFileFromResources("twentyfive/day_5_input.txt")
    val splitInput = input.split("x")

    val freshList = splitInput[0].split("\n")
    val available = splitInput[1].split("\n")
        .filter { !it.isEmpty() }
        .map { it.trim().toLong() }

    val freshRanges = freshList
        .filter { !it.isEmpty() }
        .map {
            val range = it.split("-")
            range[0].toLong()..range[1].toLong()
        }.toList()

    return Pair(freshRanges, available)
}
