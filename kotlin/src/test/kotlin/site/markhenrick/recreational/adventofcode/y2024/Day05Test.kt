package site.markhenrick.recreational.adventofcode.y2024

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import site.markhenrick.recreational.adventofcode.y2024.Day05.findMiddle
import site.markhenrick.recreational.adventofcode.y2024.Day05.parseInput
import site.markhenrick.recreational.adventofcode.y2024.Day05.solvePart1
import site.markhenrick.recreational.adventofcode.y2024.Day05.validate
import site.markhenrick.recreational.getChallengeInput
import java.util.stream.Stream

class Day05Test {
    @ParameterizedTest
    @MethodSource
    fun part1(input: String, expected: Int) {
        assertThat(solvePart1(input)).isEqualTo(expected)
    }

    @Test
    fun parseInequalities() {
        val result = parseInput(sampleInput)
        val inequalities = result.first.toList()
        assertThat(inequalities).hasSize(21)
        assertThat(inequalities[0]).isEqualTo(47 to 53)
        assertThat(inequalities[20]).isEqualTo(53 to 13)
    }

    @Test
    fun parseNumberings() {
        val result = parseInput(sampleInput)
        val numberings = result.second.toList().map { it.toList() }
        assertThat(numberings).hasSize(6)
        assertThat(numberings[0]).isEqualTo(listOf(75, 47, 61, 53, 29))
        assertThat(numberings[5]).isEqualTo(listOf(97, 13, 75, 29, 47))
    }

    @ParameterizedTest
    @MethodSource
    fun recogniseValidNumberings(input: List<Int>, rule: Pair<Int, Int>) {
        assertThat(validate(rule, input)).isTrue()
    }

    @ParameterizedTest
    @MethodSource
    fun recogniseInvalidNumberings(input: List<Int>, rule: Pair<Int, Int>) {
        assertThat(validate(rule, input)).isFalse()
    }

    @ParameterizedTest
    @MethodSource
    fun findMiddleTest(input: List<Int>, expected: Int) {
        assertThat(findMiddle(input)).isEqualTo(expected)
    }

    companion object {
        private val sampleInput = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13
    
            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """.trimIndent()
        private val challengeInput = getChallengeInput(2024, 5)

        @JvmStatic
        fun part1(): Stream<Arguments> = Stream.of(
            Arguments.of(sampleInput, 143),
            Arguments.of(challengeInput, 5091)
        )

        @JvmStatic
        fun recogniseValidNumberings(): Stream<Arguments> = Stream.of(
            Arguments.of(listOf(75, 97, 47, 61, 53), 47 to 53),
            Arguments.of(listOf(75, 97, 47, 61, 53), 47 to 61),
            Arguments.of(listOf(75, 97, 47, 61, 53), 47 to 99),
            Arguments.of(listOf(75, 97, 47, 61, 53),  1 to 47),
            Arguments.of(listOf(75, 97, 47, 61, 53), 99 to 47),
            Arguments.of(listOf(75, 97, 47, 61, 53), 47 to  1),
            Arguments.of(listOf(75, 97, 47, 61, 53),  1 to 99),
            Arguments.of(listOf(75, 97, 47, 61, 53), 99 to  1),
        )

        @JvmStatic
        fun recogniseInvalidNumberings(): Stream<Arguments> = Stream.of(
            Arguments.of(listOf(75, 97, 47, 61, 53), 97 to 75),
        )

        @JvmStatic
        fun findMiddleTest(): Stream<Arguments> = Stream.of(
            Arguments.of(listOf(75, 47, 61, 53, 29), 61),
            Arguments.of(listOf(97, 61, 53, 29, 13), 53),
            Arguments.of(listOf(75, 29, 13), 29),
        )
    }
}