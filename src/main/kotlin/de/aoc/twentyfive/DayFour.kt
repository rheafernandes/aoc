package org.example.de.aoc.twentyfive

import org.example.de.common.FileReader

enum class Direction(val delta: Pair<Int, Int>) {
    FORWARDS(0 to 1),
    BACKWARDS(0 to -1),
    UPWARDS(-1 to 0),
    DOWNWARDS(1 to 0),
    DIAGONAL_RIGHT_UP(-1 to 1),
    DIAGONAL_RIGHT_DOWN(1 to 1),
    DIAGONAL_LEFT_UP(-1 to -1),
    DIAGONAL_LEFT_DOWN(1 to -1)
}

fun solveDayFourPartOne(): Int {
    val toiletRollGrid = getInput()
    val rollsAccessed = toiletRollGrid.mapIndexed { rowIndex, row ->
        row.filterIndexed { columnIndex, char ->
            char == "@" && canAccessRoll(toiletRollGrid, rowIndex, columnIndex)
        }.count()
    }.sum()
    return rollsAccessed
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

private fun canAccessRoll(toiletRollGrid: List<List<String>>, rowIndex: Int, columnIndex: Int): Boolean {
    val adjacentRollCount = Direction.entries.count() { direction: Direction ->
        val tempRowIndex = rowIndex + direction.delta.first
        val tempColumnIndex = columnIndex + direction.delta.second

        tempRowIndex in toiletRollGrid.indices && tempColumnIndex in toiletRollGrid[0].indices &&
                toiletRollGrid[tempRowIndex][tempColumnIndex] == "@"
    }
    return adjacentRollCount < 4
}