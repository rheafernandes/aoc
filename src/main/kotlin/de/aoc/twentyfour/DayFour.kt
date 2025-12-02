package org.example.de.aoc.twentyfour

import org.example.de.common.FileReader

val searchCharSequence = listOf('X', 'M', 'A', 'S')

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
    val inputMatrix = getInput()
    var sum = 0
    for (i in inputMatrix.indices) {
        for (j in inputMatrix[0].indices) {
            val currentChar = inputMatrix[i][j]
            if (currentChar == searchCharSequence[0]) {
                for (direction in Direction.entries) {
                    val result = recursiveSearch(
                        i + direction.delta.first,
                        j + direction.delta.second,
                        1,
                        inputMatrix,
                        direction
                    )
                    sum += result
                }
            }
        }
    }
    return sum
}

fun solveDayFourPartTwo(): Int {
    return 0
}


private fun recursiveSearch(
    row: Int,
    column: Int,
    nextChar: Int,
    inputMatrix: List<List<Char>>,
    direction: Direction
): Int {
    if (nextChar >= searchCharSequence.size) {
        return 1
    }

    if (row < 0 || row >= inputMatrix.size || column < 0 || column >= inputMatrix[0].size) {
        return 0
    }

    val currentChar = inputMatrix[row][column]
    if (currentChar == searchCharSequence[nextChar]) {
        return recursiveSearch(
            row + direction.delta.first,
            column + direction.delta.second,
            nextChar + 1,
            inputMatrix,
            direction
        )
    }
    return 0
}

private fun getInput(): List<List<Char>> {
    val input = FileReader.readFileFromResources("twentyfour/day_4_input.txt")
    return input.split("\n").map { line -> line.trim().toList() }
}