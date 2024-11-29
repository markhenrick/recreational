package site.markhenrick.recreational.adventofcode.y2016

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import site.markhenrick.recreational.adventofcode.y2016.Day03.solvePart1
import site.markhenrick.recreational.adventofcode.y2016.Day03.solvePart2
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

    @ParameterizedTest
    @MethodSource
    fun parsePart1(input: String, expected: List<List<String>>) {
        assertThat(Day03.parsePart1(input).toList()).containsExactlyInAnyOrderElementsOf(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun parsePart2(input: String, expected: List<List<String>>) {
        assertThat(Day03.parsePart2(input).toList()).containsExactlyInAnyOrderElementsOf(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun recogniseValidTriangles(input: Day03.Triangle) {
        assertThat(input.isValid()).isTrue()
    }

    @ParameterizedTest
    @MethodSource
    fun recogniseInvalidTriangles(input: Day03.Triangle) {
        assertThat(input.isValid()).isFalse()
    }

    companion object {
        // Added my own since we only get one test case given
        private const val sampleInput1 = """
            5  10   25
            1 1 1
            1  2  2
            3  4    5
            1 1 2
            1 1 3
            1 2     3
            1 2 4
        """
        private const val sampleInput2 = """
            101 301 501
            102 302 502
            103 303 503
            201 401 601
            202 402 602
            203 403 603
        """
        private val challengeInput = getChallengeInput(2016, 3)

        @JvmStatic
        fun part1(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput1, 3),
            Arguments.of(challengeInput, 869)
        )

        @JvmStatic
        fun part2(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput2, 6),
            Arguments.of(challengeInput, 1544)
        )

        @JvmStatic
        fun parsePart1(): Stream<Arguments> = Stream.of(
            Arguments.of(
                sampleInput1,
                listOf(
                    listOf("5", "10", "25"),
                    listOf("1", "1", "1"),
                    listOf("1", "2", "2"),
                    listOf("3", "4", "5"),
                    listOf("1", "1", "2"),
                    listOf("1", "1", "3"),
                    listOf("1", "2", "3"),
                    listOf("1", "2", "4"),
                )
            )
        )

        @JvmStatic
        fun parsePart2(): Stream<Arguments> = Stream.of(
            Arguments.of(
                sampleInput2,
                listOf(
                    listOf("101", "102", "103"),
                    listOf("201", "202", "203"),
                    listOf("301", "302", "303"),
                    listOf("401", "402", "403"),
                    listOf("501", "502", "503"),
                    listOf("601", "602", "603"),
                )
            )
        )

        @JvmStatic
        fun recogniseValidTriangles(): Stream<Day03.Triangle> = Stream.of(
            Day03.Triangle(1, 1, 1),
            Day03.Triangle(1, 2, 2),
            Day03.Triangle(3, 4, 5),
        )
            .flatMap { permuteTriangles(it) }

        @JvmStatic
        fun recogniseInvalidTriangles(): Stream<Day03.Triangle> = Stream.of(
            Day03.Triangle(5, 10, 25),
            Day03.Triangle(1, 1, 2),
            Day03.Triangle(1, 1, 3),
            Day03.Triangle(1, 2, 3),
            Day03.Triangle(1, 2, 4),
        )
            .flatMap { permuteTriangles(it) }

        // TODO create a generic List permuter and remove this
        private fun permuteTriangles(seed: Day03.Triangle): Stream<Day03.Triangle> = Stream.of(
            seed,
            Day03.Triangle(seed.a, seed.c, seed.b),
            Day03.Triangle(seed.b, seed.a, seed.c),
            Day03.Triangle(seed.b, seed.c, seed.a),
            Day03.Triangle(seed.c, seed.a, seed.b),
            Day03.Triangle(seed.c, seed.b, seed.a),
        )
    }
}
