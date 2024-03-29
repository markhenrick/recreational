package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.data.IntVec3;
import site.markhenrick.recreational.common.TestUtil;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day02Test {
	private static final String SAMPLE_INPUT = """
Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
""";

	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(8, SAMPLE_INPUT),
			arguments(2406, TestUtil.getResourceAsString("AoC/input/2023/day02.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input)
	{
		assertThat(Day02.part1(input)).isEqualTo(expected);
	}

	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(2286, SAMPLE_INPUT),
			arguments(78375, TestUtil.getResourceAsString("AoC/input/2023/day02.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(int expected, String input)
	{
		assertThat(Day02.part2(input)).isEqualTo(expected);
	}

	@Test
	void parseGame() {
		val input = "Game 34: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
		val expectedId = 34;
		val expectedVecs = List.of(
			new IntVec3(3, 1, 6),
			new IntVec3(6, 3, 0),
			new IntVec3(14, 3, 15)
		);

		val result = Day02.parseGame(input);

		assertThat(result.id()).isEqualTo(expectedId);
		assertThat(result.vecs().toList()).isEqualTo(expectedVecs);
	}

	@Test
	void minimalVec() {
		val vecs = Stream.of(
			new IntVec3(0, 2, 1),
			new IntVec3(1, 3, 4),
			new IntVec3(0, 1, 1)
		);
		val expected = new IntVec3(1, 3, 4);
		assertThat(Day02.minimalVec(vecs)).isEqualTo(expected);
	}
}
