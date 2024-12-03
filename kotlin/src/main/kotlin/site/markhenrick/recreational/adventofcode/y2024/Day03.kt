package site.markhenrick.recreational.adventofcode.y2024

object Day03 {
    private val pattern = Regex("mul\\((\\d+),(\\d+)\\)")

    fun solvePart1(input: String) = pattern.findAll(input)
        .map {
            assert(it.groups.size == 2 + 1)
            it.groups[1]!!.value.toInt() * it.groups[2]!!.value.toInt()
        }
        .sum()
}