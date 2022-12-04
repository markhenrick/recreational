package site.markhenrick.recreational.adventofcode.y2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.recreational.adventofcode.y2022.Day02.Shape.*;

public class Day02Test {
	private static final int WIN = 2;
	private static final int LOSS = 0;
	private static final int DRAW = 1;

	private static final String SAMPLE_INPUT = """
A Y
B X
C Z
""";

	@ParameterizedTest
	@CsvSource({
		"ROCK, 1",
		"PAPER, 2",
		"SCISSORS, 3",
	})
	void shapeScore(Day02.Shape shape, int expectedScore) {
		assertThat(shape.score()).isEqualTo(expectedScore);
	}

	static Stream<Arguments> fight() {
		return Stream.of(
			arguments(ROCK, ROCK, DRAW),
			arguments(ROCK, PAPER, LOSS),
			arguments(ROCK, SCISSORS, WIN),
			arguments(PAPER, ROCK, WIN),
			arguments(PAPER, PAPER, DRAW),
			arguments(PAPER, SCISSORS, LOSS),
			arguments(SCISSORS, ROCK, LOSS),
			arguments(SCISSORS, PAPER, WIN),
			arguments(SCISSORS, SCISSORS, DRAW)
		);
	}

	@ParameterizedTest
	@MethodSource
	void fight(Day02.Shape ours, Day02.Shape theirs, int expectedScore) {
		assertThat(ours.fight(theirs)).isEqualTo(3 * expectedScore);
	}

	@ParameterizedTest
	@MethodSource("fight")
	void findMatch(Day02.Shape expected, Day02.Shape theirs, int desiredOutcome) {
		assertThat(Day02.Shape.findMatch(theirs, desiredOutcome)).isEqualTo(expected);
	}

	static Stream<Arguments> part1Test() {
		return Stream.of(
			Arguments.of(SAMPLE_INPUT, 15),
			arguments(TestUtil.getResourceAsString("AoC/input/2022/day02.txt"), 11063)
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1Test(String input, int expected) {
		assertThat(Day02.part1(input)).isEqualTo(expected);
	}

	static Stream<Arguments> part2Test() {
		return Stream.of(
			Arguments.of(SAMPLE_INPUT, 12),
			arguments(TestUtil.getResourceAsString("AoC/input/2022/day02.txt"), 10349)
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2Test(String input, int expected) {
		assertThat(Day02.part2(input)).isEqualTo(expected);
	}
}
