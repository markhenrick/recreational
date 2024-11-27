package site.markhenrick.recreational.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.sqrt

private val UNIT_NEG_X = IntVec2(-1, 0)
private val UNIT_NEG_Y = IntVec2(0, -1)
private val UNIT_NEG_ALL = IntVec2(-1, -1)

class IntVec2Test {
    /*
    TODO also consider "parts commuted" tests, for properties where swapping x and y in the input swaps them output/
    maintains the scalar (i.e. basically all of them?)
    This would allow eliminating some argument cases
     */
    @ParameterizedTest
    @MethodSource
    fun addition(left: IntVec2, right: IntVec2, expected: IntVec2) {
        assertThat(left + right).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("addition")
    fun additionCommuted(left: IntVec2, right: IntVec2, expected: IntVec2) {
        assertThat(right + left).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun negation(input: IntVec2, expected: IntVec2) {
        assertThat(-input).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("negation")
    fun negationCommuted(input: IntVec2, expected: IntVec2) {
        assertThat(-expected).isEqualTo(input)
    }

    @ParameterizedTest
    @MethodSource("negation")
    fun negationCancelled(input: IntVec2, ignored: IntVec2) {
        assertThat(-(-input)).isEqualTo(input)
    }

    @ParameterizedTest
    @MethodSource
    fun subtraction(left: IntVec2, right: IntVec2, expected: IntVec2) {
        assertThat(left - right).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("subtraction")
    fun subtractionAndNegationCommuted(left: IntVec2, right: IntVec2, expected: IntVec2) {
        assertThat(right - left).isEqualTo(-expected)
    }

    @ParameterizedTest
    @MethodSource
    fun metricSize(input: IntVec2, expected: Int) {
        assertThat(input.metricSize()).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("metricSize")
    fun metricNegatedSize(input: IntVec2, expected: Int) {
        assertThat((-input).metricSize()).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun euclideanSize(input: IntVec2, expected: Double) {
        assertThat(input.euclideanSize()).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("euclideanSize")
    fun euclideanSizeNegated(input: IntVec2, expected: Double) {
        assertThat((-input).euclideanSize()).isEqualTo(expected)
    }

    // TODO
//    @ParameterizedTest
//    @MethodSource
//    fun metricDistance(left: IntVec2, right: IntVec2, expected: Int) {
//        TODO("Not yet implemented")
//    }
//
//    @ParameterizedTest
//    @MethodSource("metricDistance")
//    fun metricDistanceCommuted(left: IntVec2, right: IntVec2, expected: Int) {
//        TODO("Not yet implemented")
//    }
//
//    @ParameterizedTest
//    @MethodSource
//    fun euclideanDistance(left: IntVec2, right: IntVec2, expected: Double) {
//        TODO("Not yet implemented")
//    }
//
//    @ParameterizedTest
//    @MethodSource("euclideanDistance")
//    fun euclideanDistanceCommuted(left: IntVec2, right: IntVec2, expected: Double) {
//        TODO("Not yet implemented")
//    }

    companion object {
        @JvmStatic
        fun addition(): Stream<Arguments> = Stream.of(
            Arguments.of(ORIGIN, ORIGIN, ORIGIN),
            Arguments.of(ORIGIN, UNIT_X, UNIT_X),
            Arguments.of(ORIGIN, UNIT_Y, UNIT_Y),
            Arguments.of(UNIT_X, UNIT_Y, UNIT_ALL),
            Arguments.of(UNIT_X, UNIT_NEG_X, ORIGIN),
            Arguments.of(UNIT_Y, UNIT_NEG_Y, ORIGIN),
            Arguments.of(UNIT_X, UNIT_X, IntVec2(2, 0)),
            Arguments.of(UNIT_Y, UNIT_Y, IntVec2(0, 2)),
            Arguments.of(UNIT_NEG_X, UNIT_NEG_X, IntVec2(-2, 0)),
            Arguments.of(UNIT_NEG_Y, UNIT_NEG_Y, IntVec2(0, -2)),
            Arguments.of(IntVec2(2, 3), IntVec2(-4, 5), IntVec2(-2, 8)),
        )

        @JvmStatic
        fun negation(): Stream<Arguments> = Stream.of(
            Arguments.of(ORIGIN, ORIGIN),
            Arguments.of(UNIT_X, UNIT_NEG_X),
            Arguments.of(UNIT_Y, UNIT_NEG_Y),
            Arguments.of(UNIT_ALL, UNIT_NEG_ALL),
            Arguments.of(IntVec2(2, -3), IntVec2(-2, 3)),
        )

        @JvmStatic
        fun subtraction(): Stream<Arguments> = Stream.of(
            Arguments.of(ORIGIN, ORIGIN, ORIGIN),
            Arguments.of(ORIGIN, UNIT_X, UNIT_NEG_X),
            Arguments.of(ORIGIN, UNIT_Y, UNIT_NEG_Y),
            Arguments.of(UNIT_X, UNIT_Y, IntVec2(1, -1)),
            Arguments.of(UNIT_X, UNIT_NEG_X, IntVec2(2, 0)),
            Arguments.of(UNIT_NEG_X, UNIT_X, IntVec2(-2, 0)),
            Arguments.of(UNIT_Y, UNIT_NEG_Y, IntVec2(0, 2)),
            Arguments.of(UNIT_NEG_Y, UNIT_Y, IntVec2(0, -2)),
            Arguments.of(UNIT_X, UNIT_X, ORIGIN),
            Arguments.of(UNIT_Y, UNIT_Y, ORIGIN),
            Arguments.of(UNIT_NEG_X, UNIT_NEG_X, ORIGIN),
            Arguments.of(UNIT_NEG_Y, UNIT_NEG_Y, ORIGIN),
            Arguments.of(IntVec2(2, 3), IntVec2(-4, 5), IntVec2(6, -2)),
        )

        @JvmStatic
        fun metricSize(): Stream<Arguments> = Stream.of(
            Arguments.of(ORIGIN, 0),
            Arguments.of(UNIT_X, 1),
            Arguments.of(UNIT_Y, 1),
            Arguments.of(UNIT_NEG_X, 1),
            Arguments.of(UNIT_NEG_Y, 1),
            Arguments.of(UNIT_ALL, 2),
            Arguments.of(UNIT_NEG_ALL, 2),
            Arguments.of(IntVec2(1, -1), 2),
            Arguments.of(IntVec2(2, -3), 5),
        )

        @JvmStatic
        fun euclideanSize(): Stream<Arguments> = Stream.of(
            Arguments.of(ORIGIN, 0.0),
            Arguments.of(UNIT_X, 1.0),
            Arguments.of(UNIT_Y, 1.0),
            Arguments.of(UNIT_NEG_X, 1.0),
            Arguments.of(UNIT_NEG_Y, 1.0),
            Arguments.of(IntVec2(2, 0), 2.0),
            Arguments.of(IntVec2(0, 2), 2.0),
            Arguments.of(UNIT_ALL, sqrt(2.0)),
            Arguments.of(UNIT_NEG_ALL, sqrt(2.0)),
            Arguments.of(IntVec2(1, -1), sqrt(2.0)),
            Arguments.of(IntVec2(2, -3), sqrt(2.0 * 2.0 + 3.0 * 3.0)),
        )
    }
}