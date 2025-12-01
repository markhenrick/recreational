package site.markhenrick.recreational.adventofcode.y2025

import kotlin.math.max
import kotlin.math.min

private const val MODULO = 100
private const val INITIAL = 50

object Day01 {
    fun solvePart1(input: String): Int = getSequence(input)
        .filter(Int::isZeroModulo)
        .count()

    fun solvePart2(input: String): Int = getSequence(input)
        .windowed(2, 1) { (a, b) -> zeroCrosses(a, b) }
        .sum()

    private fun parseInstruction(input: String): Int {
        assert(input[0] == 'L' || input[0] == 'R')
        val sign = if (input[0] == 'L') -1 else 1
        val value = input.substring(1).toInt()
        return sign * value
    }

    private fun getSequence(input: String): Sequence<Int> = input.lineSequence()
        .map(::parseInstruction)
        .runningFold(INITIAL) { a, b -> a + b }

    // TODO replace this with a less-stupid version. I tried several times, but always failed to satisfy every edge case
    internal fun zeroCrosses(start: Int, end: Int): Int {
        var count = 0
        for (i in min(start, end)..max(start, end)) {
            if (i.isZeroModulo()) count++
        }
        if (start.isZeroModulo()) count--
        return count
    }
}

private fun Int.isZeroModulo(): Boolean = Math.floorMod(this, MODULO) == 0
