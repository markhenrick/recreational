package site.markhenrick.recreational.adventofcode.y2025

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import site.markhenrick.recreational.getChallengeInput
import java.util.stream.Stream

class Day05Test {
    @ParameterizedTest
    @MethodSource
    fun part1(input: String, expected: Long) {
        assertThat(Day05.part1(input)).isEqualTo(expected)
    }

    companion object {
        private val sampleInput = """
            3-5
            10-14
            16-20
            12-18
            
            1
            5
            8
            11
            17
            32
        """.trimIndent()
        private val challengeInput = getChallengeInput(2025, 5)

        @JvmStatic
        fun part1(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, 3),
            Arguments.of(challengeInput, 623)
        )
    }
}
