package site.markhenrick.recreational.adventofcode.y2016

import site.markhenrick.recreational.common.WHITESPACE_REGEX
import kotlin.math.max

object Day03 {
    fun solvePart1(input: String): Int = parsePart1(input).countValid()

    fun solvePart2(input: String): Int = parsePart2(input).countValid()

    fun parsePart1(input: String): Sequence<List<String>> = input.lineSequence()
        .filter { it.isNotBlank() }
        .map { it.trim() }
        .map { it.split(WHITESPACE_REGEX) }
        .map {
            assert(it.size == 3)
            it
        }

    // yay parsing...
    fun parsePart2(input: String): Sequence<List<String>> = sequence {
        var left = mutableListOf<String>()
        var middle = mutableListOf<String>()
        var right = mutableListOf<String>()
        for (line in input.lineSequence()) {
            if (line.isBlank()) continue
            val parts = line.trim().split(WHITESPACE_REGEX)
            assert(parts.size == 3)

            left.add(parts[0])
            middle.add(parts[1])
            right.add(parts[2])

            if (left.size == 3) {
                yield(left)
                yield(middle)
                yield(right)

                left = mutableListOf()
                middle = mutableListOf()
                right = mutableListOf()
            }
        }
        require(left.isEmpty()) { "Excess input: $left" }
    }

    private fun Sequence<List<String>>.countValid(): Int = this
        .map { it.map { string -> string.toInt() } }
        .map { Triangle(it[0], it[1], it[2]) }
        .filter { it.isValid() }
        .count()

    // TODO is there any point to this, over an extension method on Triple?
    data class Triangle(
        val a: Int,
        val b: Int,
        val c: Int,
    ) {
        fun largestSide(): Int = max(max(a, b), c)
        fun perimeter(): Int = a + b + c
        fun isValid(): Boolean = 2 * largestSide() < perimeter()

        init {
            require(a > 0 && b > 0 && c > 0) { "Sides must all be > 0" }
        }
    }
}