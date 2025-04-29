package site.markhenrick.recreational.adventofcode.y2024

private typealias Inequality = Pair<Int, Int>

object Day05 {
    fun solvePart1(input: String): Int {
        val (inequalitiesSeq, numberingsSeq) = parseInput(input)
        val inequalities = inequalitiesSeq.toList()
        return numberingsSeq
            .map { it.toList() }
            .filter {
                inequalities.all { rule -> validate(rule, it) }
            }
            .map { findMiddle(it) }
            .sum()
    }

    // Following the general principle of "don't convert to a list unless *you* have to - consumers can"
    fun parseInput(input: String): Pair<Sequence<Inequality>, Sequence<Sequence<Int>>> {
        // Could probably do this slightly more efficiently by writing a split() function
        val allLines = input.lines()
        val inequalities = allLines.asSequence()
            .takeWhile { it.isNotBlank() }
            .map { it.split('|') }
            .map {
                assert(it.size == 2) { it }
                it
            }
            .map { it[0].toInt() to it[1].toInt() }
        val numberings = allLines.asSequence()
            .dropWhile { it.isNotBlank() }
            .drop(1)
            .map { it.splitToSequence(',').map(String::toInt) }
        return inequalities to numberings
    }

    // Honestly given the input size it doesn't seem worth trying any clever
    // data structures right now. Not even a map of page numbers to positions, since they're small.
    // So we'll just do the naive O(n * m) and indexOf() way
    // Since I'm conjecturing that indexOf() would be faster than a map then that makes it ~O(|inequalities|)
    fun validate(rule: Inequality, numbering: List<Int>): Boolean {
        val firstIndex = numbering.indexOf(rule.first)
        if (firstIndex == -1) return true
        val secondIndex = numbering.indexOf(rule.second)
        return secondIndex == -1 || secondIndex > firstIndex
    }

    fun <T> findMiddle(list: List<T>): T {
        assert(list.size % 2 == 1)
        return list[list.size / 2]
    }
}