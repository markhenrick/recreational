package site.markhenrick.recreational.adventofcode.y2019

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import site.markhenrick.recreational.getChallengeInput

class Day02Test {
    @Test
    fun part1() {
        assertThat(Day02.part1(challengeInput)).isEqualTo(2894520)
    }

    @Test
    fun part2() {
        assertThat(Day02.part2(challengeInput)).isEqualTo(9342)
    }

    companion object {
        private val challengeInput = getChallengeInput(2019, 2)
    }
}
