package site.markhenrick.adventofcode.y2020;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.adventofcode.y2020.Day07.Production;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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

	private Day07.BinaryRelation<Character> relation;

	// Can't do a nested test suite since then I can't use static methods so can't use MethodSource

	@BeforeEach
	void setUp() {
		// a-() b->c<->d->e
		relation = new Day07.BinaryRelation<>(List.of('a', 'b', 'c', 'd', 'e'));
		relation.set('a', 'a');
		relation.set('b', 'c');
		relation.set('c', 'd');
		relation.set('d', 'c');
		relation.set('d', 'e');
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

	@SuppressWarnings("unused")
	static Stream<Arguments> solveDay1() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 4)
		);
	}

	@ParameterizedTest
	@CsvSource({
		"a, a, true",
		"a, b, false",
		"b, b, false",
		"b, c, true",
		"c, b, false",
		"c, d, true",
		"d, c, true",
		"b, d, false",
	})
	void binaryRelationRetention(final char source, final char target, final boolean expected) {
		assertThat(relation.get(source, target)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
		"a, 1",
		"b, 1",
		"c, 1",
		"d, 2",
		"e, 0",
	})
	void binaryRelationTargetSize(final char source, final int expected) {
		assertThat(relation.targetSize(source)).isEqualTo(expected);
	}

	@Test
	void transitivelyClose() {
		// a-() b->c<->d->e
		final var expected = new Day07.BinaryRelation<>(List.of('a', 'b', 'c', 'd', 'e'));
		expected.set('a', 'a');
		expected.set('b', 'c');
		expected.set('c', 'd');
		expected.set('d', 'c');
		expected.set('d', 'e');

		expected.set('b', 'd');
		expected.set('b', 'e');
		expected.set('c', 'e');

		relation.transitivelyClose();

		assertThat(relation).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource
	void solveDay1(final String input, final int expected) {
		assertThat(Day07.solveDay1(input)).isEqualTo(expected);
	}
}
