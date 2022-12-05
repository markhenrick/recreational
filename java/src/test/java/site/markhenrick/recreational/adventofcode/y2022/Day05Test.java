package site.markhenrick.recreational.adventofcode.y2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day05Test {
	private static final String SAMPLE_INPUT = """
    [D]
[N] [C]
[Z] [M] [P]
 1   2   3\s

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
""";

	static Stream<Arguments> parseSetupAndReadOffStacks() {
		return Stream.of(
			arguments(SAMPLE_INPUT, "NDP"),
			arguments(TestUtil.getResourceAsString("AoC/input/2022/day05.txt"), "VQBLBCRRF")
		);
	}

	@ParameterizedTest
	@MethodSource
	public void parseSetupAndReadOffStacks(String input, String expectedTops) {
		var stacks = Day05.parseSetup(input);
		assertThat(stacks).hasSize(expectedTops.length() + 1);
		var actualTops = Day05.readOffStacks(stacks);
		assertThat(actualTops).isEqualTo(expectedTops);
	}

	static Stream<Arguments> parseMoves() {
		return Stream.of(
			arguments(
				SAMPLE_INPUT,
				new Day05.Move(1, 2, 1),
				new Day05.Move(1, 1, 2)
			),
			arguments(
				TestUtil.getResourceAsString("AoC/input/2022/day05.txt"),
				new Day05.Move(1, 8, 4),
				new Day05.Move(1, 3, 9)
			)
		);
	}

	@ParameterizedTest
	@MethodSource
	public void parseMoves(String input, Day05.Move firstMove, Day05.Move lastMove) {
		var result = Day05.parseMoves(input).toList();
		assertThat(result.get(0)).isEqualTo(firstMove);
		assertThat(result.get(result.size() - 1)).isEqualTo(lastMove);
	}

	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(SAMPLE_INPUT, "CMZ"),
			arguments(TestUtil.getResourceAsString("AoC/input/2022/day05.txt"), "SBPQRSCDF")
		);
	}

	@ParameterizedTest
	@MethodSource
	public void part1(String input, String expectedTops) {
		assertThat(Day05.part1(input)).isEqualTo(expectedTops);
	}
}
