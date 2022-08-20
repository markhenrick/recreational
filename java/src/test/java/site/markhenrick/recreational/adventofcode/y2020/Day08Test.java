package site.markhenrick.recreational.adventofcode.y2020;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day08Test {
	private static final String SAMPLE_INPUT = """
nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6
""";

	private static final String MY_INPUT = TestUtil.getResourceAsString("AoC/input/2020/day08.txt");

	@SuppressWarnings("unused")
	static Stream<Arguments> solvePart1() {
		return Stream.of(
			Arguments.arguments(SAMPLE_INPUT, 5),
			Arguments.arguments(MY_INPUT, 1797)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solvePart1(final String input, final int expected) {
		assertThat(Day08.solvePart1(input)).isEqualTo(expected);
	}
}
