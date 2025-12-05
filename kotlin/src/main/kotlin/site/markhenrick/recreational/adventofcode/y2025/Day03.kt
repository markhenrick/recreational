package site.markhenrick.recreational.adventofcode.y2025

import site.markhenrick.recreational.common.digitToInt

object Day03 {
    fun solvePart1(input: String): Int = input.lineSequence()
        .map(::findMaximalJoltage)
        .sum()

    internal fun findMaximalJoltage(input: String): Int {
        require(input.length >= 2)

        var maxFirst: Char? = null
        var maxFirstI: Int? = null
        for (i in 0..<input.length - 1) {
            val c = input[i]
            if (maxFirst == null || c > maxFirst) {
                maxFirst = c
                maxFirstI = i
            }
        }
        assert(maxFirst != null)
        assert(maxFirstI != null)

        var maxSecond: Char? = null
        for (i in maxFirstI!! + 1..<input.length) {
            val c = input[i]
            if (maxSecond == null || c > maxSecond) {
                maxSecond = c
            }
        }
        assert(maxSecond != null)

        return 10 * maxFirst!!.digitToInt() + maxSecond!!.digitToInt()
    }
}
