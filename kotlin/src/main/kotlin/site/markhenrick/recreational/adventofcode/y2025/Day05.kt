package site.markhenrick.recreational.adventofcode.y2025

object Day05 {
    fun part1(input: String): Int {
        val ranges = input.lineSequence()
            .takeWhile { !it.isBlank() }
            .map {
                val parts = it.split('-')
                require(parts.size == 2)
                return@map LongRange(parts[0].toLong(), parts[1].toLong())
            }
            .toList()
        // There's probably a more efficient way to just continue the above stream
        return input.lineSequence()
            .dropWhile { !it.isBlank() }
            .drop(1)
            .map { it.toLong() }
            .filter { id -> ranges.any { id in it } }
            .count()
    }
}
