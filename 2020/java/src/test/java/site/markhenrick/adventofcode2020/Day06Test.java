package site.markhenrick.adventofcode2020;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.adventofcode2020.common.TestUtil.getResourceAsString;

// No assertions inspection is false-positiving
@SuppressWarnings({"SpellCheckingInspection", "JUnitTestMethodWithNoAssertions", "DynamicRegexReplaceableByCompiledPattern"})
class Day06Test {
	private static final String SAMPLE_INPUT = """
abc

a
b
c

ab
ac

a
a
a
a

b
""";
	private static final String MY_INPUT = getResourceAsString("input/day06.txt");

	@ParameterizedTest
	@CsvSource({
		"abc, 3",
		"anbnc, 3",
		"abnac, 3",
		"ananana, 1",
		"b, 1",
	})
	void decodeGroup(final String input, final int expected) {
		final var inputWithNewlines = input.replaceAll("n", "\n");
		assertThat(Day06.decodeGroup(inputWithNewlines)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> solvePart1() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 11),
			arguments(MY_INPUT, 6625)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solvePart1(final String input, final int expected) {
		assertThat(Day06.solvePart1(input)).isEqualTo(expected);
	}
}
