package site.markhenrick.recreational.adventofcode.y2019

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import site.markhenrick.recreational.getChallengeInput

class Day01Test {
    @Test
    fun part1() {
        assertThat(Day01.solvePart1(challengeInput)).isEqualTo(3226822)
    }

    @Test
    fun part2() {
        assertThat(Day01.solvePart2(challengeInput)).isEqualTo(4837367)
    }

    @ParameterizedTest
    @CsvSource(
        "12, 2",
        "14, 2",
        "1969, 654",
        "100756, 33583",
    )
    fun fuelRequired(input: Int, expected: Int) {
        assertThat(Day01.fuelRequired(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "14, 2",
        "1969, 654+216+70+21+5",
        "100756, 33583+11192+3728+1240+411+135+43+12+2",
    )
    fun fuelSequence(input: Int, expectedRaw: String) {
        val expectedList = expectedRaw.split("+").map { it.toInt()}
        val actualList = Day01.fuelSequence(input).toList()
        assertThat(actualList).isEqualTo(expectedList)
    }

    companion object {
        private val challengeInput = getChallengeInput(2019, 1)
    }
}
