package site.markhenrick.recreational.adventofcode.y2025

import site.markhenrick.recreational.common.isSortedBy

object Day05 {
    fun part1(input: String): Int {
        val ranges = getRanges(input).toList()
        return input.lineSequence()
            .dropWhile { !it.isBlank() }
            .drop(1)
            .map { it.toLong() }
            .filter { id -> ranges.any { id in it } }
            .count()
    }

    fun part2(input: String): Long {
        val ranges = getRanges(input)
            .sortedBy { it.first }
            .toMutableList()
        return mergeIntervals(ranges)
            .sumOf { it.last - it.first + 1 }
    }

    // Not the best code here
    // TODO allocate less
    tailrec fun mergeIntervals(intervals: MutableList<LongRange>): MutableList<LongRange> {
        if (intervals.size <= 1) return intervals
        // TODO use a heap or something rather than this hackiness
        if (!intervals.isSortedBy { it.first }) {
            intervals.sortBy { it.first }
        }
        val result = mutableListOf<LongRange>()
        val firstInterval = intervals.first()
        var newLast = firstInterval.last
        for (interval in intervals.drop(1)) {
            assert(interval.first >= firstInterval.first)
            assert(interval.first <= interval.last)
            if (interval.last <= newLast) {
                continue // Redundant interval
            } else if (interval.first <= firstInterval.last) {
                newLast = interval.last
                continue
            } else {
                // Reached end of other intervals within first
                result.add(interval)
            }
        }
        result.addFirst(LongRange(firstInterval.first, newLast))
        assert(result.size <= intervals.size)
        return if (result.size < intervals.size) {
            mergeIntervals(result)
        } else {
            // TODO ow oof my absolute stack
            val next = mergeIntervals(intervals.drop(1).toMutableList())
            next.addFirst(firstInterval)
            return next
        }
    }

    private fun getRanges(input: String): Sequence<LongRange> = input.lineSequence()
        .takeWhile { !it.isBlank() }
        .map {
            val parts = it.split('-')
            require(parts.size == 2)
            return@map LongRange(parts[0].toLong(), parts[1].toLong())
        }
}
