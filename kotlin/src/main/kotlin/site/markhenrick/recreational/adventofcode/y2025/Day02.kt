package site.markhenrick.recreational.adventofcode.y2025

import site.markhenrick.recreational.common.base10DigitCount
import site.markhenrick.recreational.common.intPow

object Day02 {
    private val repeatingPattern = Regex("^(.+)\\1+$")

    fun solvePart1(input: String): Long = getIds(input)
        .filter { !isValid1(it) }
        .sum()

    // TODO, see if I can optimise the iteration instead for this one. Implement addition on a base-10 char[]
    fun solvePart2(input: String): Long = getIds(input)
        .filter { !isValid2(it) }
        .sum()

    internal fun isValid1(id: Long): Boolean {
        val digitCount = base10DigitCount(id)
        if (digitCount % 2 == 1L) return true
        val divisor = intPow(10, digitCount / 2)
        return id / divisor != id % divisor
    }

    // Not doing a fancy solution here
    internal fun isValid2(id: Long): Boolean = !repeatingPattern.matches(id.toString())

    private fun getIds(input: String): Sequence<Long> = input.splitToSequence(',')
        .flatMap {
            val parts = it.split('-')
            assert(parts.size == 2)
            return@flatMap LongRange(parts[0].toLong(), parts[1].toLong())
        }
}
