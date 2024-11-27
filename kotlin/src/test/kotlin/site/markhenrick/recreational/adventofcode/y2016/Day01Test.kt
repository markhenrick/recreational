package site.markhenrick.recreational.adventofcode.y2016

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import site.markhenrick.recreational.common.IntVec2
import site.markhenrick.recreational.common.ORIGIN
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
    fun walk(input: String, expected: List<IntVec2>) {
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
                    ORIGIN,
                    IntVec2(1, 0),
                    IntVec2(2, 0),
                    IntVec2(2, 1),
                    IntVec2(2, 2),
                    IntVec2(2, 3),
                )
            ),
            Arguments.of(
                "R2, R2, R2",
                listOf(
                    ORIGIN,
                    IntVec2(1, 0),
                    IntVec2(2, 0),
                    IntVec2(2, -1),
                    IntVec2(2, -2),
                    IntVec2(1, -2),
                    IntVec2(0, -2),
                )
            ),
            Arguments.of(
                "R5, L5, R5, R3",
                listOf(
                    ORIGIN,
                    IntVec2(1, 0),
                    IntVec2(2, 0),
                    IntVec2(3, 0),
                    IntVec2(4, 0),
                    IntVec2(5, 0),
                    IntVec2(5, 1),
                    IntVec2(5, 2),
                    IntVec2(5, 3),
                    IntVec2(5, 4),
                    IntVec2(5, 5),
                    IntVec2(6, 5),
                    IntVec2(7, 5),
                    IntVec2(8, 5),
                    IntVec2(9, 5),
                    IntVec2(10, 5),
                    IntVec2(10, 4),
                    IntVec2(10, 3),
                    IntVec2(10, 2),
                )
            ),
            Arguments.of(
                "R8, R4, R4, R8",
                listOf(
                    ORIGIN,
                    IntVec2(1, 0),
                    IntVec2(2, 0),
                    IntVec2(3, 0),
                    IntVec2(4, 0),
                    IntVec2(5, 0),
                    IntVec2(6, 0),
                    IntVec2(7, 0),
                    IntVec2(8, 0),
                    IntVec2(8, -1),
                    IntVec2(8, -2),
                    IntVec2(8, -3),
                    IntVec2(8, -4),
                    IntVec2(7, -4),
                    IntVec2(6, -4),
                    IntVec2(5, -4),
                    IntVec2(4, -4),
                    IntVec2(4, -3),
                    IntVec2(4, -2),
                    IntVec2(4, -1),
                    IntVec2(4, 0),
                    IntVec2(4, 1),
                    IntVec2(4, 2),
                    IntVec2(4, 3),
                    IntVec2(4, 4),
                )
            )
        )
    }
}