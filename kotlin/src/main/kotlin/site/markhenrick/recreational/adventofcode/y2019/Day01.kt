package site.markhenrick.recreational.adventofcode.y2019

object Day01 {
    fun solvePart1(input: String): Int = input.lineSequence()
        .map { it.toInt() }
        .map { fuelRequired(it) }
        .sum()

    fun solvePart2(input: String): Int = input.lineSequence()
        .map { it.toInt() }
        .flatMap { fuelSequence(it) }
        .sum()

    internal fun fuelRequired(mass: Int): Int = (mass / 3) - 2

    internal fun fuelSequence(mass: Int): Sequence<Int> = generateSequence(mass) { fuelRequired(it) }
        .drop(1)
        .takeWhile { it > 0 }
}
