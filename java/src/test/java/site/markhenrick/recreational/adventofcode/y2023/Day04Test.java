package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.recreational.common.CollectionUtil.bitSetOf;

public class Day04Test {
	private static final String SAMPLE_INPUT = """
Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
""";

	static Stream<Arguments> part1() {
		return Stream.of(
				arguments(13, SAMPLE_INPUT),
				arguments(21138, TestUtil.getResourceAsString("AoC/input/2023/day04.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input)
	{
		assertThat(Day04.part1(input)).isEqualTo(expected);
	}

	static Stream<Arguments> part2() {
		return Stream.of(
				arguments(30, SAMPLE_INPUT),
				arguments(7185540, TestUtil.getResourceAsString("AoC/input/2023/day04.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(int expected, String input)
	{
		assertThat(Day04.part2(input)).isEqualTo(expected);
	}

	@Test
	void parseCard() {
		val input = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
		val expectedLeft = bitSetOf(41, 48, 83, 86, 17);
		val expectedRight = bitSetOf(83, 86, 6, 31, 17, 9, 48, 53);

		val actual = Day04.parseCard(input);

		assertThat(actual.l()).isEqualTo(expectedLeft);
		assertThat(actual.r()).isEqualTo(expectedRight);
	}

	@ParameterizedTest
	@CsvSource({
			"0,0",
			"1,1",
			"2,2",
			"4,8",
	})
	void scoreIntersection(int size, int score) {
		assertThat(Day04.scoreIntersection(size)).isEqualTo(score);
	}
}
