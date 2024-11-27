package site.markhenrick.recreational.adventofcode.y2016

import site.markhenrick.recreational.common.IntVec2
import site.markhenrick.recreational.common.ORIGIN
import site.markhenrick.recreational.common.UNIT_X
import site.markhenrick.recreational.common.UNIT_Y
import site.markhenrick.recreational.common.wrapMod

// Positive y = north
// TODO I feel like using complex numbers could make this simpler
private val COMPASS = listOf(UNIT_Y, UNIT_X, -UNIT_Y, -UNIT_X)

// TODO oh dear this is in the top-level package namespace. Will have to decide what to do about that before day 2
fun solvePart1(input: String): Int = walk(input).metricSize()

// TODO consider parsing the input up front

fun walk(input: String): IntVec2 {
    var location = ORIGIN
    var headingIndex = 0
    for (instruction in input.splitToSequence(", ")) {
        if (instruction[0] == 'L') {
            headingIndex = (headingIndex - 1 ) wrapMod COMPASS.size
        } else {
            assert(instruction[0] == 'R')
            headingIndex = (headingIndex + 1 ) wrapMod COMPASS.size
        }
        val stepCount = instruction.substring(1).toInt()
        location += COMPASS[headingIndex] * stepCount
    }
    return location

}