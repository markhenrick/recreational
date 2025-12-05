package site.markhenrick.recreational.adventofcode.y2025

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import site.markhenrick.recreational.getChallengeInput
import java.util.stream.Stream

class Day04Test {
    @ParameterizedTest
    @MethodSource
    fun part1(input: String, expected: Int) {
        assertThat(Day04.solvePart1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun part2(input: String, expected: Int) {
        assertThat(Day04.solvePart2(input)).isEqualTo(expected)
    }

    companion object {
        private val sampleInput = """
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@. 
        """.trimIndent()
        private val challengeInput = getChallengeInput(2025, 4)

        @JvmStatic
        fun part1(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, 13),
            Arguments.of(challengeInput, 1344)
        )

        @JvmStatic
        fun part2(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, 43),
            Arguments.of(challengeInput, 8112)
        )
    }
}
