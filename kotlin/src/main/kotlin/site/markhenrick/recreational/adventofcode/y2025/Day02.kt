package site.markhenrick.recreational.adventofcode.y2025

import site.markhenrick.recreational.common.base10DigitCount
import site.markhenrick.recreational.common.intPow

object Day02 {
    fun solvePart1(input: String): Long = getIds(input)
        .filter { !isValid(it) }
        .sum()

    internal fun isValid(id: Long): Boolean {
        val digitCount = base10DigitCount(id)
        if (digitCount % 2 == 1) return true
        val divisor = intPow(10, digitCount / 2)
        return id / divisor != id % divisor
    }

    private fun getIds(input: String): Sequence<Long> = input.splitToSequence(',')
        .flatMap {
            val parts = it.split('-')
            assert(parts.size == 2)
            return@flatMap LongRange(parts[0].toLong(), parts[1].toLong())
        }
}
