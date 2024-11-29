package site.markhenrick.recreational.adventofcode.y2016

import site.markhenrick.recreational.common.WHITESPACE_REGEX
import kotlin.math.max

object Day03 {
    fun solvePart1(input: String): Int =
        input.lineSequence()
            .filter { it.isNotBlank() }
            .map { it.trim() }
            .map { it.split(WHITESPACE_REGEX) }
            .map { it.map { string -> string.toInt() } }
            .map {
                assert(it.size == 3)
                Triangle(it[0], it[1], it[2])
            }
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