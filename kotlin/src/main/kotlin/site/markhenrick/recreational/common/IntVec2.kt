package site.markhenrick.recreational.common

import kotlin.math.abs
import kotlin.math.sqrt

data class IntVec2(
    val i: Int,
    val j: Int,
) {
    operator fun plus(other: IntVec2): IntVec2 = IntVec2(i + other.i, j + other.j)
    operator fun times(scalar: Int): IntVec2 = IntVec2(scalar * i, scalar * j)

    operator fun unaryMinus(): IntVec2 = this * -1
    operator fun minus(other: IntVec2): IntVec2 = this + -other

    // size = distance from origin
    fun metricSize(): Int = abs(i) + abs(j)
    fun euclideanSize(): Double = sqrt((i * i + j * j).toDouble())

    fun metricDistance(other: IntVec2): Int = (this - other).metricSize()
    fun euclideanDistance(other: IntVec2): Double = (this - other).euclideanSize()
}

val ORIGIN = IntVec2(0, 0)
val UNIT_X = IntVec2(1, 0)
val UNIT_Y = IntVec2(0, 1)
val UNIT_ALL = IntVec2(1, 1)