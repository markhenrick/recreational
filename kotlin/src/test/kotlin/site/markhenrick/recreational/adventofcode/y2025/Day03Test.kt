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

    @ParameterizedTest
    @CsvSource(
        "987654321111111, 98",
        "811111111111119, 89",
        "234234234234278, 78",
        "818181911112111, 92",
    )
    fun findMaximalJoltage(input: String, expected: Int) {
        assertThat(Day03.findMaximalJoltage(input)).isEqualTo(expected)
    }

    companion object {
        private val challengeInput = getChallengeInput(2025, 3)
    }
}
