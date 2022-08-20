package site.markhenrick.recreational.adventofcode.y2020;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

// No assertions inspection is false-positiving
@SuppressWarnings({"SpellCheckingInspection", "JUnitTestMethodWithNoAssertions"})
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
	private static final String MY_INPUT = TestUtil.getResourceAsString("AoC/input/2020/day06.txt");

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
		assertThat(Day06.SOLVE_PART_1.apply(input)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> solvePart2() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 6),
			arguments(MY_INPUT, 3360)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solvePart2(final String input, final int expected) {
		assertThat(Day06.SOLVE_PART_2.apply(input)).isEqualTo(expected);
	}
}
