package site.markhenrick.recreational.adventofcode.y2024

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import site.markhenrick.recreational.adventofcode.y2024.Day01.computePart1
import site.markhenrick.recreational.adventofcode.y2024.Day01.computePart2
import site.markhenrick.recreational.adventofcode.y2024.Day01.parseInput
import site.markhenrick.recreational.adventofcode.y2024.Day01.solvePart1
import site.markhenrick.recreational.adventofcode.y2024.Day01.solvePart2
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
    fun parse(input: String, expectedLeft: List<Int>, expectedRight: List<Int>) {
        val (left, right) = parseInput(input)
        assertThat(left).isEqualTo(expectedLeft)
        assertThat(right).isEqualTo(expectedRight)
    }

    @ParameterizedTest
    @MethodSource
    fun compute(left: List<Int>, right: List<Int>, expected: Int) {
        assertThat(computePart1(left, right)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun compute2(left: List<Int>, right: List<Int>, expected: Int) {
        assertThat(computePart2(left, right)).isEqualTo(expected)
    }

    companion object {
        private val sampleInput = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent()
        private val challengeInput = getChallengeInput(2024, 1)

        @JvmStatic
        fun part1(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, 11),
            Arguments.of(challengeInput, 2057374),
        )

        @JvmStatic
        fun part2(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, 31),
            Arguments.of(challengeInput, 23177084),
        )

        @JvmStatic
        fun parse(): Stream<Arguments> = Stream.of(
            Arguments.of(
                sampleInput,
                listOf(3, 4, 2, 1, 3, 3),
                listOf(4, 3, 5, 3, 9 ,3),
            ),
        )

        @JvmStatic
        fun compute(): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(3, 4, 2, 1, 3, 3),
                listOf(4, 3, 5, 3, 9 ,3),
                11,
            ),
        )

        @JvmStatic
        fun compute2(): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(3, 4, 2, 1, 3, 3),
                listOf(4, 3, 5, 3, 9 ,3),
                31,
            ),
        )
    }
}