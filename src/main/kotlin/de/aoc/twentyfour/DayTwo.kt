package org.example.de.aoc.twentyfour

import org.example.de.common.FileReader

fun solveDayTwoPartOne(): Int {
    val records = getInput()
    return records.count { record ->
        isGraduallyAscending(record) || isGraduallyDescending(record)
    }
}

fun solveDayTwoPartTwo(): Int {
    val records = getInput()
    return records.count { record ->
        val safe = isGraduallyAscending(record) || isGraduallyDescending(record)
        if(!safe) {
            canRecordBeFixed(record)
        } else {
            true
        }
    }
}


private fun getInput(): List<List<Int>> {
    val input = FileReader.readFileFromResources("day_2_input.txt")

    return input.split("\n").map { line ->
        line.split("\\s".toRegex()).map { it.trim().toInt() }
    }
}

private fun isGraduallyAscending(record: List<Int>): Boolean {
    return record.asSequence().zipWithNext { a, b ->
        a < b && b - a <= 3
    }.all { it }
}

private fun isGraduallyDescending(record: List<Int>): Boolean {
    return record.asSequence().zipWithNext { a, b ->
        a > b && a - b <= 3
    }.all { it }
}

private fun canRecordBeFixed(record:List<Int>): Boolean {
    for(i in record.indices) {
        val modifiedRecord = record.toMutableList()
        modifiedRecord.removeAt(i)
        if(isGraduallyAscending(modifiedRecord) || isGraduallyDescending(modifiedRecord)) {
            return true
        }
    }
    return false
}
