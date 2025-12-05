package site.markhenrick.recreational.adventofcode.y2025

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import site.markhenrick.recreational.getChallengeInput

class Day03Test {
    @Test
    fun part1() {
        assertThat(Day03.solvePart1(challengeInput)).isEqualTo(17207)
    }

    @Test
    fun part2() {
        assertThat(Day03.solvePart2(challengeInput)).isEqualTo(170997883706617)
    }

    @ParameterizedTest
    @CsvSource(
        "987654321111111, 2, 98",
        "811111111111119, 2, 89",
        "234234234234278, 2, 78",
        "818181911112111, 2, 92",
        // part 2
        "987654321111111, 12, 987654321111",
        "811111111111119, 12, 811111111119",
        "234234234234278, 12, 434234234278",
        "818181911112111, 12, 888911112111",
    )
    fun findMaximalJoltage(input: String, digitCount: Int, expected: Long) {
        assertThat(Day03.findMaximalJoltage(input, digitCount)).isEqualTo(expected)
    }

    companion object {
        private val challengeInput = getChallengeInput(2025, 3)
    }
}
