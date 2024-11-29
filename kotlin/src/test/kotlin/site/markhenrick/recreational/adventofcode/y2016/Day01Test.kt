package site.markhenrick.recreational.adventofcode.y2016

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import site.markhenrick.recreational.adventofcode.y2016.Day01.solvePart1
import site.markhenrick.recreational.adventofcode.y2016.Day01.solvePart2
import site.markhenrick.recreational.adventofcode.y2016.Day01.walk
import site.markhenrick.recreational.common.ComplexInt
import site.markhenrick.recreational.common.i
import site.markhenrick.recreational.common.plus
import site.markhenrick.recreational.getChallengeInput
import java.util.stream.Stream

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
        private val challengeInput = getChallengeInput(2016, 1)

        @JvmStatic
        fun part1(): Stream<Arguments> = Stream.of(
            Arguments.of("R2, L3", 5),
            Arguments.of("R2, R2, R2", 2),
            Arguments.of("R5, L5, R5, R3", 12),
            Arguments.of(challengeInput, 332),
        )

        @JvmStatic
        fun part2(): Stream<Arguments> = Stream.of(
            Arguments.of("R8, R4, R4, R8", 4),
            Arguments.of(challengeInput, 166),
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