package site.markhenrick.recreational.adventofcode.y2024

import site.markhenrick.recreational.common.WHITESPACE_REGEX
import kotlin.math.abs

// Putting things in objects purely for namespacing. Not ideal but seems a bit better than a package for each day
object Day01 {
    fun solvePart1(input: String): Int {
        val (left, right) = parseInput(input)
        return computePart1(left, right)
    }

    // We do have to eagerly parse and sort everything first, so we can find each ordinal
    fun parseInput(input: String): Pair<List<Int>, List<Int>> {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        for (line in input.lineSequence()) {
            val parts = line.splitToSequence(WHITESPACE_REGEX)
                .filter { it.isNotBlank() }
                .map { it.toInt() }
                .toList()
            assert(parts.size == 2)
            left.add(parts[0])
            right.add(parts[1])
        }
        return left to right
    }

    fun computePart1(left: List<Int>, right: List<Int>): Int = left.asSequence()
        .sorted()
        .zip(right.asSequence().sorted())
        .map { (x, y) -> abs(x - y) }
        .sum()
}