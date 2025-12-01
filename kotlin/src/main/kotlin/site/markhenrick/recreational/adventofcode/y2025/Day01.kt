package site.markhenrick.recreational.adventofcode.y2025

private const val MODULO = 100
private const val INITIAL = 50

object Day01 {
    fun solvePart1(input: String): Int = input.lineSequence()
        .map {
            assert(it[0] == 'L' || it[0] == 'R')
            val sign = if (it[0] == 'L') -1 else 1
            val value = it.substring(1).toInt()
            return@map sign * value
        }
        .runningFold(INITIAL) { a, b -> Math.floorMod(a + b, MODULO) }
        .filter { it == 0 }
        .count()
}
