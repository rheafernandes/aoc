package org.example.de.aoc.twentyfive

import org.example.de.common.FileReader
import kotlin.math.abs


fun solveDayOnePartOne(): Int {
    val directionPairs = getInput()
    var dialPosition = 50
    var code = 0

    directionPairs.forEach { pair ->
        if (pair.first == 'R') {
            dialPosition += pair.second
        } else {
            dialPosition -= pair.second
        }
        dialPosition %= 100

        if (dialPosition < 0) {
            dialPosition += 100
        } else if (dialPosition == 0) {
            code++
        }
    }
    return code
}

fun solveDayOnePartTwo(): Int {
    val directionPairs = getInput()
    var dialPosition = 50
    var code = 0
    var wasDialPosition0 = false

    directionPairs.forEach { pair ->
        if (pair.first == 'R') {
            dialPosition += pair.second
        } else {
            dialPosition -= pair.second
        }

        val rotations = abs(dialPosition / 100)
        if (rotations > 0)
            code += rotations
        else if (dialPosition == 0) {
            code++
            wasDialPosition0 = true
        }

        dialPosition %= 100

        if (dialPosition < 0) {
            if(!wasDialPosition0) {
                code++
                wasDialPosition0 = false
            }
            dialPosition += 100
        }
    }
    return code
}

fun getInput(): List<Pair<Char, Int>> {
    val input = FileReader.readFileFromResources("twentyfive/day_1_input.txt")
    val lines = input.split("\n")
    return lines.map {
        Pair(it[0], it.drop(1).toInt())
    }.toList()
}