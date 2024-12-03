package site.markhenrick.recreational.adventofcode.y2024

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import site.markhenrick.recreational.adventofcode.y2024.Day02.isValidReport
import site.markhenrick.recreational.adventofcode.y2024.Day02.parseInput
import site.markhenrick.recreational.adventofcode.y2024.Day02.removedLevelVariants
import site.markhenrick.recreational.adventofcode.y2024.Day02.solvePart1
import site.markhenrick.recreational.adventofcode.y2024.Day02.solvePart2
import site.markhenrick.recreational.getChallengeInput
import java.util.stream.Stream

class Day02Test {
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
    fun parse(input: String, expected: List<List<Int>>) {
        val output = parseInput(input).map { it.toList() }.toList()
        assertThat(output).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun recogniseValidReports(input: List<Int>) {
        assertThat(isValidReport(input)).isTrue()
    }

    @ParameterizedTest
    @MethodSource
    fun recogniseInvalidReports(input: List<Int>) {
        assertThat(isValidReport(input)).isFalse()
    }

    @ParameterizedTest
    @MethodSource
    fun removedLevelVariantsTest(input: List<Int>, expected: List<List<Int>>) {
        assertThat(removedLevelVariants(input).toList()).isEqualTo(expected)
    }

    companion object {
        private val sampleInput = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent()
        private val challengeInput = getChallengeInput(2024, 2)

        @JvmStatic
        fun part1(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, 2),
            Arguments.of(challengeInput, 371),
        )

        @JvmStatic
        fun part2(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, 4),
            Arguments.of(challengeInput, 426),
        )

        @JvmStatic
        fun parse(): Stream<Arguments> = Stream.of(
            Arguments.of(
                sampleInput,
                listOf(
                    listOf(7, 6, 4, 2, 1),
                    listOf(1, 2, 7, 8, 9),
                    listOf(9, 7, 6, 2, 1),
                    listOf(1, 3, 2, 4, 5),
                    listOf(8, 6, 4, 4, 1),
                    listOf(1, 3, 6, 7, 9),
                )
            ),
        )

        @JvmStatic
        fun recogniseValidReports(): Stream<List<Int>> = Stream.of(
            listOf(7, 6, 4, 2, 1),
            listOf(1, 3, 6, 7, 9),
        )

        @JvmStatic
        fun recogniseInvalidReports(): Stream<List<Int>> = Stream.of(
            listOf(1, 2, 7, 8, 9),
            listOf(9, 7, 6, 2, 1),
            listOf(1, 3, 2, 4, 5),
            listOf(8, 6, 4, 4, 1),
        )

        @JvmStatic
        fun removedLevelVariantsTest(): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(7, 6, 4, 2, 1),
                listOf(
                    listOf(7, 6, 4, 2, 1),
                    listOf(6, 4, 2, 1),
                    listOf(7, 4, 2, 1),
                    listOf(7, 6, 2, 1),
                    listOf(7, 6, 4, 1),
                    listOf(7, 6, 4, 2),
                )
            )
        )
    }
}