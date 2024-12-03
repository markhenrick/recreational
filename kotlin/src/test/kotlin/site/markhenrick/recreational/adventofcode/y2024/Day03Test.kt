package site.markhenrick.recreational.adventofcode.y2024

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import site.markhenrick.recreational.adventofcode.y2024.Day03.solvePart1
import site.markhenrick.recreational.adventofcode.y2024.Day03.solvePart2
import site.markhenrick.recreational.getChallengeInput
import java.util.stream.Stream

class Day03Test {
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

    companion object {
        private val sampleInput1 = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        private val sampleInput2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
        private val challengeInput = getChallengeInput(2024, 3)

        @JvmStatic
        fun part1(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput1, 161),
            Arguments.of(challengeInput, 178794710),
        )

        @JvmStatic
        fun part2(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput2, 48),
            Arguments.of(challengeInput, 76729637),
        )
    }
}