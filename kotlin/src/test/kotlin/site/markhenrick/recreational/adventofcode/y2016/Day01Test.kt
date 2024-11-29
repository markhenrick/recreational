package site.markhenrick.recreational.adventofcode.y2016

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import site.markhenrick.recreational.common.ComplexInt
import site.markhenrick.recreational.common.i
import site.markhenrick.recreational.common.plus
import java.util.stream.Stream

// TODO set up file reading for day 2
private const val ACTUAL_INPUT = "L4, L3, R1, L4, R2, R2, L1, L2, R1, R1, L3, R5, L2, R5, L4, L3, R2, R2, L5, L1, R4, L1, R3, L3, R5, R2, L5, R2, R1, R1, L5, R1, L3, L2, L5, R4, R4, L2, L1, L1, R1, R1, L185, R4, L1, L1, R5, R1, L1, L3, L2, L1, R2, R2, R2, L1, L1, R4, R5, R53, L1, R1, R78, R3, R4, L1, R5, L1, L4, R3, R3, L3, L3, R191, R4, R1, L4, L1, R3, L1, L2, R3, R2, R4, R5, R5, L3, L5, R2, R3, L1, L1, L3, R1, R4, R1, R3, R4, R4, R4, R5, R2, L5, R1, R2, R5, L3, L4, R1, L5, R1, L4, L3, R5, R5, L3, L4, L4, R2, R2, L5, R3, R1, R2, R5, L5, L3, R4, L5, R5, L3, R1, L1, R4, R4, L3, R2, R5, R1, R2, L1, R4, R1, L3, L3, L5, R2, R5, L1, L4, R3, R3, L3, R2, L5, R1, R3, L3, R2, L1, R4, R3, L4, R5, L2, L2, R5, R1, R2, L4, L4, L5, R3, L4"

class Day01Test {
    @ParameterizedTest
    @MethodSource
    fun part1(input: String, expected: Int) {
        assertThat(solvePart1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun part2(input: String, expected: Int) {
        assertThat(solvePart2(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun walk(input: String, expected: List<ComplexInt>) {
        assertThat(walk(input).toList()).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun part1(): Stream<Arguments> = Stream.of(
            Arguments.of("R2, L3", 5),
            Arguments.of("R2, R2, R2", 2),
            Arguments.of("R5, L5, R5, R3", 12),
            Arguments.of(ACTUAL_INPUT, 332),
        )

        @JvmStatic
        fun part2(): Stream<Arguments> = Stream.of(
            Arguments.of("R8, R4, R4, R8", 4),
            Arguments.of(ACTUAL_INPUT, 166),
        )

        @JvmStatic
        fun walk(): Stream<Arguments> = Stream.of(
            Arguments.of(
                "R2, L3",
                listOf(
                    0 + 0.i,
                    1 + 0.i,
                    2 + 0.i,
                    2 + 1.i,
                    2 + 2.i,
                    2 + 3.i,
                )
            ),
            Arguments.of(
                "R2, R2, R2",
                listOf(
                    0 + 0.i,
                    1 + 0.i,
                    2 + 0.i,
                    2 + -1.i,
                    2 + -2.i,
                    1 + -2.i,
                    0 + -2.i,
                )
            ),
            Arguments.of(
                "R5, L5, R5, R3",
                listOf(
                    0 + 0.i,
                    1 + 0.i,
                    2 + 0.i,
                    3 + 0.i,
                    4 + 0.i,
                    5 + 0.i,
                    5 + 1.i,
                    5 + 2.i,
                    5 + 3.i,
                    5 + 4.i,
                    5 + 5.i,
                    6 + 5.i,
                    7 + 5.i,
                    8 + 5.i,
                    9 + 5.i,
                    10 + 5.i,
                    10 + 4.i,
                    10 + 3.i,
                    10 + 2.i,
                )
            ),
            Arguments.of(
                "R8, R4, R4, R8",
                listOf(
                    0 + 0.i,
                    1 + 0.i,
                    2 + 0.i,
                    3 + 0.i,
                    4 + 0.i,
                    5 + 0.i,
                    6 + 0.i,
                    7 + 0.i,
                    8 + 0.i,
                    8 + -1.i,
                    8 + -2.i,
                    8 + -3.i,
                    8 + -4.i,
                    7 + -4.i,
                    6 + -4.i,
                    5 + -4.i,
                    4 + -4.i,
                    4 + -3.i,
                    4 + -2.i,
                    4 + -1.i,
                    4 + 0.i,
                    4 + 1.i,
                    4 + 2.i,
                    4 + 3.i,
                    4 + 4.i,
                )
            )
        )
    }
}