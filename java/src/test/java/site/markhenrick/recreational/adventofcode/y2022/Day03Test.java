package site.markhenrick.recreational.adventofcode.y2022;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

public class Day03Test {
	private static final String SAMPLE_INPUT = """
vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw
""";

	@ParameterizedTest
	@CsvSource({
			"aa, a",
			"abac, a",
			"baca, a",
			"vJrwpWtwJgWrhcsFMMfFFhFp, p",
			"jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL, L",
			"PmmdzqPrVvPwwTWBwg, P",
			"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn, v",
			"ttgJtRGJQctTZtZT, t",
			"CrZsJsPPZsGzwwsLwLmpwMDw, s",
	})
	void findDuplicate(String input, char duplicate) {
		assertThat(Day03.findDuplicate(input.toCharArray())).isEqualTo(duplicate);
	}

	@ParameterizedTest
	@CsvSource({
			"a, 1",
			"z, 26",
			"A, 27",
			"Z, 52",
	})
	void getPriority(char character, int expected) {
		assertThat(Day03.getPriority(character)).isEqualTo(expected);
	}

	static Stream<Arguments> part1() {
		return Stream.of(
				arguments(SAMPLE_INPUT, 157),
				arguments(TestUtil.getResourceAsString("AoC/input/2022/day03.txt"), 7446)
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(String input, int expected) {
		assertThat(Day03.part1(input)).isEqualTo(expected);
	}
}
