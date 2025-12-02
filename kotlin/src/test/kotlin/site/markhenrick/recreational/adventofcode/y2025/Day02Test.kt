package site.markhenrick.recreational.adventofcode.y2025

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import site.markhenrick.recreational.getChallengeInput
import java.util.stream.Stream

class Day02Test {
    @ParameterizedTest
    @MethodSource
    fun part1(input: String, expected: Long) {
        assertThat(Day02.solvePart1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(longs = [
        1,
        12,
        123,
        1221,
        101,
    ])
    fun validIds(id: Long) {
        assertThat(Day02.isValid(id)).isTrue
    }

    @ParameterizedTest
    @ValueSource(longs = [
        11,
        22,
        99,
        1010,
        1188511885,
        222222,
        446446,
        38593859,
    ])
    fun invalidIds(id: Long) {
        assertThat(Day02.isValid(id)).isFalse
    }

    companion object {
        private val sampleInput = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
        private val challengeInput = getChallengeInput(2025, 2)

        @JvmStatic
        fun part1(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, 1227775554),
            Arguments.of(challengeInput, 34826702005)
        )
    }
}
