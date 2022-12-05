package site.markhenrick.recreational.adventofcode.y2022;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
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

	static Stream<Arguments> splitArray() {
		return Stream.of(
				arguments("abcdef".toCharArray(), List.of("a".toCharArray(), "b".toCharArray(), "c".toCharArray(), "d".toCharArray(), "e".toCharArray(), "f".toCharArray())),
				arguments("abcdef".toCharArray(), List.of("ab".toCharArray(), "cd".toCharArray(), "ef".toCharArray())),
				arguments("vJrwpWtwJgWrhcsFMMfFFhFp".toCharArray(), List.of("vJrwpWtwJgWr".toCharArray(), "hcsFMMfFFhFp".toCharArray()))
		);
	}

	@ParameterizedTest
	@MethodSource
	void splitArray(char[] input, List<char[]> expected) {
		var actual = Day03.splitArray(expected.size(), input).toList();
		assertThat(actual).hasSize(expected.size());
		// Arrays don't just work with .equals()
		for (var i = 0; i < expected.size(); i++) {
			var expectedArray = expected.get(i);
			var actualArray = actual.get(i);
			assertThat(actualArray).isEqualTo(expectedArray);
		}
	}

	@ParameterizedTest
	@CsvSource({
			"vJrwpWtwJgWrhcsFMMfFFhFp, jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL, PmmdzqPrVvPwwTWBwg, r",
			"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn, ttgJtRGJQctTZtZT, CrZsJsPPZsGzwwsLwLmpwMDw, Z",
	})
	void findCommon(String elf1, String elf2, String elf3, char expected) {
		var input = Stream.of(elf1, elf2, elf3).map(String::toCharArray);
		assertThat(Day03.findCommon(input)).isEqualTo(expected);
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

	static Stream<Arguments> part2() {
		return Stream.of(
				arguments(SAMPLE_INPUT, 70),
				arguments(TestUtil.getResourceAsString("AoC/input/2022/day03.txt"), 2646)
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(String input, int expected) {
		assertThat(Day03.part2(input)).isEqualTo(expected);
	}
}
