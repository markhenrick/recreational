package site.markhenrick.recreational.adventofcode.y2025

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import site.markhenrick.recreational.getChallengeInput
import java.util.stream.Stream

class Day01Test {
    @ParameterizedTest
    @MethodSource
    fun part1(input: String, expected: Int) {
        assertThat(Day01.solvePart1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun part2(input: String, expected: Int) {
        assertThat(Day01.solvePart2(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "0, 99, 0",
        "50, 150, 1",
        "50, 250, 2",
        "50, 1050, 10",
        "50, -18, 1", // The dial starts by pointing at 50.; The dial is rotated L68 to point at 82; during this rotation, it points at **0 once**.
        "-18, -48, 0", // The dial is rotated L30 to point at 52.
        "-48, 0, 1", // The dial is rotated R48 to point at **0**.
        "0, -5, 0", // The dial is rotated L5 to point at 95.
        "-5, 55, 1", // The dial is rotated R60 to point at 55; during this rotation, it points at **0 once**.
        "55, 0, 1",  // The dial is rotated L55 to point at **0**.
        "0, -1, 0", // The dial is rotated L1 to point at 99.
        "-1, -100, 1", // The dial is rotated L99 to point at **0**.
        "-100, -86, 0", // The dial is rotated R14 to point at 14.
        "-86, -168, 1", // The dial is rotated L82 to point at 32; during this rotation, it points at **0 once**.

    )
    fun zeroCrosses(start: Int, end: Int, expected: Int) {
        assertThat(Day01.zeroCrosses(start, end)).isEqualTo(expected)
    }

    companion object {
        private val sampleInput = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
        """.trimIndent()
        private val challengeInput = getChallengeInput(2025, 1)

        @JvmStatic
        fun part1(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, 3),
            Arguments.of(challengeInput, 1059)
        )

        @JvmStatic
        fun part2(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, 6),
            Arguments.of(challengeInput, 6305)
        )
    }
}
