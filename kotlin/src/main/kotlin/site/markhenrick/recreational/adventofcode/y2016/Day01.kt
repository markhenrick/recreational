package site.markhenrick.recreational.adventofcode.y2016

import site.markhenrick.recreational.common.ComplexInt
import site.markhenrick.recreational.common.i
import kotlin.math.abs

// TODO oh dear this is in the top-level package namespace. Will have to decide what to do about that before day 2
fun solvePart1(input: String): Int = walk(input).last().metricSize()

fun solvePart2(input: String): Int {
    val seen = mutableSetOf<ComplexInt>()
    for (location in walk(input)) {
        if (!seen.add(location)) {
            return location.metricSize()
        }
    }
    throw AssertionError("No retracted step")
}

// TODO consider parsing the input up front

fun walk(input: String): Sequence<ComplexInt> = sequence {
    var location = 0.i
    var heading = 1.i
    yield(location)
    for (instruction in input.splitToSequence(", ")) {
        if (instruction[0] == 'L') {
            heading *= 1.i
        } else {
            assert(instruction[0] == 'R')
            heading *= -1.i
        }
        val stepCount = instruction.substring(1).toInt()
        // TODO this is a really stupid/inefficient solution. Find the line intersections instead
        for (i in 1..stepCount) {
            location += heading
            yield(location)
        }
    }
}

// Not putting in the main class since it isn't really a standard op
private fun ComplexInt.metricSize(): Int = abs(real) + abs(imag)