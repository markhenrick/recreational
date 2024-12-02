package site.markhenrick.recreational.adventofcode.y2024

import site.markhenrick.recreational.common.WHITESPACE_REGEX
import site.markhenrick.recreational.common.deltas
import kotlin.math.abs

object Day02 {
    fun solvePart1(input: String): Int = parseInput(input)
        .filter { isValidReport(it.toList()) }
        .count()

    fun solvePart2(input: String): Int = parseInput(input)
        .map { reallyBadBruteForcePart2Shim(it.toList()) }
        .filter { it.any { lit -> isValidReport(lit) } }
        .count()

    fun parseInput(input: String): Sequence<Sequence<Int>> = input.lineSequence()
        .map { it.splitToSequence(WHITESPACE_REGEX).filter(String::isNotBlank).map(String::toInt) }

    fun isValidReport(report: List<Int>): Boolean {
        // Hard to do Sequence.any due to this bit
        var isIncreasing: Boolean? = null
        for (delta in report.deltas()) {
            if (abs(delta) !in 1..3) return false
            if (isIncreasing == null) {
                isIncreasing = delta > 0
            } else {
                if ((delta > 0) != isIncreasing) return false
            }
        }
        return true
    }

    // Given here as an example of how *not* to do anything right, except get a gold star quickly
    fun reallyBadBruteForcePart2Shim(seq: List<Int>): Sequence<List<Int>> = sequence {
        val asList = seq.toList()
        yield(asList)
        for (i in asList.indices) {
            val newList = mutableListOf<Int>()
            for (j in asList.indices) {
                if (i != j) newList.add(asList[j])
            }
            yield(newList)
        }
    }
}