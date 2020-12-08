package site.markhenrick.adventofcode.y2020;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.adventofcode.y2020.Day07.Production;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.adventofcode.common.TestUtil.getResourceAsString;

class Day07Test {
	private static final String SAMPLE_INPUT = """
light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.
""";
	private static final String MY_INPUT = getResourceAsString("input/2020/day07.txt");

	@SuppressWarnings("unused")
	static Stream<Arguments> solveDay1() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 4),
			arguments(MY_INPUT, 268)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solveDay1(final String input, final int expected) {
		assertThat(Day07.solveDay1(input)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> parse() {
		return Stream.of(
			arguments("dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
				new Production("dark orange", Map.of("bright white", 3, "muted yellow", 4))),
			arguments("bright white bags contain 1 shiny gold bag.",
				new Production("bright white", Map.of("shiny gold", 1))),
			arguments("faded blue bags contain no other bags.",
				new Production("faded blue", Collections.emptyMap()))
		);
	}

	@ParameterizedTest
	@MethodSource
	void parse(final String input, final Production expected) {
		assertThat(Production.parse(input)).isEqualTo(expected);
	}
}
