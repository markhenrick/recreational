package site.markhenrick.recreational.adventofcode.y2025

import site.markhenrick.recreational.common.digitToInt
import site.markhenrick.recreational.common.intPow

object Day03 {
    fun solvePart1(input: String): Long = input.lineSequence()
        .map { findMaximalJoltage(it, 2) }
        .sum()

    internal fun findMaximalJoltage(input: String, digits: Int): Long = findMaximalJoltageInternal(input, 0, digits - 1)

    // No this isn't tail recursive, but we know the stack depth will only be 12 at most
    // It could be made tailrec by passing a running sum, but no real need at this problem size
    // `digit` = exponent of base, or reverse index of the string. So 2 = hundreds column
    private fun findMaximalJoltageInternal(input: String, startAt: Int, digit: Int): Long {
        require(digit >= 0)
        require(startAt in input.indices)
        require(input.length > digit)

        var max: Char? = null
        var maxI: Int? = null
        for (i in startAt..<input.length - digit) {
            val c = input[i]
            if (max == null || c > max) {
                max = c
                maxI = i
            }
        }
        assert(max != null)
        assert(maxI != null)

        val thisResult = intPow(10, digit.toLong()) * max!!.digitToInt()
        return if (digit > 0) {
            thisResult + findMaximalJoltageInternal(input, maxI!! + 1, digit - 1)
        } else {
            thisResult
        }
    }
}
