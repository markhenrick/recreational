package site.markhenrick.recreational.adventofcode.y2016

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import site.markhenrick.recreational.adventofcode.y2016.Day02.solvePart1
import site.markhenrick.recreational.adventofcode.y2016.Day02.solvePart2
import site.markhenrick.recreational.getChallengeInput
import java.util.stream.Stream

class Day02Test {
    @ParameterizedTest
    @MethodSource
    fun part1(input: String, expected: String) {
        assertThat(solvePart1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun part2(input: String, expected: String) {
        assertThat(solvePart2(input)).isEqualTo(expected)
    }

    companion object {
        private val sampleInput = """
            ULL
            RRDDD
            LURDL
            UUUUD
        """.trimIndent()
        private val challengeInput = getChallengeInput(2016, 2)

        @JvmStatic
        fun part1(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, "1985"),
            Arguments.of(challengeInput, "82958"),
        )

        @JvmStatic
        fun part2(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, "5DB3"),
            Arguments.of(challengeInput, "B3DB8"),
        )
    }
}