package site.markhenrick.recreational.adventofcode.y2024

import site.markhenrick.recreational.common.WHITESPACE_REGEX
import site.markhenrick.recreational.common.deltas
import kotlin.math.abs

object Day02 {
    fun solvePart1(input: String): Int = parseInput(input)
        .filter { isValidReport(it) }
        .count()

    fun parseInput(input: String): Sequence<Sequence<Int>> = input.lineSequence()
        .map { it.splitToSequence(WHITESPACE_REGEX).filter(String::isNotBlank).map(String::toInt) }

    fun isValidReport(report: Sequence<Int>): Boolean {
        // Hard to do Sequence.any due to this bit
        var isIncreasing: Boolean? = null
        for (delta in report.toList().deltas()) {
            if (abs(delta) !in 1..3) return false
            if (isIncreasing == null) {
                isIncreasing = delta > 0
            } else {
                if ((delta > 0) != isIncreasing) return false
            }
        }
        return true
    }
}