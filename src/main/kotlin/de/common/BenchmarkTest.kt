package org.example.de.common

fun <R> benchmarkSolveDayFourPartTwo(runs: Int = 10, warmups: Int = 3, challenge: () -> R) {
    // warm up the JIT
    repeat(warmups) {  challenge() }

    val timesNanos = LongArray(runs)
    for (i in 0 until runs) {
        val start = System.nanoTime()
        challenge()
        val end = System.nanoTime()
        timesNanos[i] = end - start
    }

    val best = timesNanos.minOrNull() ?: 0L
    val avg = timesNanos.average()
    println(
        "solveDayFourPartTwo — runs=$runs warmups=$warmups avg=${"%.3f".format(avg / 1_000_000.0)} ms best=${
            "%.3f".format(
                best / 1_000_000.0
            )
        } ms"
    )
}