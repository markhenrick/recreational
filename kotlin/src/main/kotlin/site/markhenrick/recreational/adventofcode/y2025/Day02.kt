package site.markhenrick.recreational.adventofcode.y2025

object Day02 {
    private val squarePattern = Regex("(.+)\\1")

    fun solvePart1(input: String): Long = getIds(input)
        .filter { !isValid(it) }
        .sum()

    // TODO temporary String-based version
    internal fun isValid(id: Long): Boolean = !squarePattern.matches(id.toString())

    private fun getIds(input: String): Sequence<Long> = input.splitToSequence(',')
        .flatMap {
            val parts = it.split('-')
            assert(parts.size == 2)
            return@flatMap LongRange(parts[0].toLong(), parts[1].toLong())
        }
}
