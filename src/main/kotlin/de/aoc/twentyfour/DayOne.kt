package org.example.de.aoc.twentyfour

import org.example.de.common.FileReader
import kotlin.math.abs

fun solveDayOnePartOne(): Int {
    val (pointAList, pointBList) = getInput()
    return totalDistance(pointAList, pointBList)
}

fun solveDayOnePartTwo(): Int  {
    val (pointAList, pointBList) = getInput()
    val pointBFreqMap = pointBList.groupingBy { it }.eachCount()
    return pointAList.fold(0){acc, pointA ->
        acc + (pointA * (pointBFreqMap[pointA] ?: 0))
    }
}

private fun getInput(): Pair<MutableList<Int>, MutableList<Int>> {
    val input = FileReader.readFileFromResources("twentyfour/day_1_input.txt")

    val pointAList = mutableListOf<Int>()
    val pointBList = mutableListOf<Int>()
    input.split("\n").forEach { line ->
        val (pointA, pointB) = line.split("\\s+".toRegex()).map { it.trim().toInt() }
        pointAList.add(pointA)
        pointBList.add(pointB)
    }
    return pointAList to pointBList
}

private fun totalDistance(pointAList: MutableList<Int>, pointBList: MutableList<Int>): Int {
    pointAList.sort()
    pointBList.sort()
    var sum = 0
    for (i in pointAList.indices) {
        sum +=  abs(pointBList[i] - pointAList[i])
    }
    return sum
}

