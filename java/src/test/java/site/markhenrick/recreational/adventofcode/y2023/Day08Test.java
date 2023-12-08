package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.FunctionalUtil;
import site.markhenrick.recreational.common.TestUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day08Test {
	private static final String SAMPLE_INPUT_1 = """
RL

AAA = (BBB, CCC)
BBB = (DDD, EEE)
CCC = (ZZZ, GGG)
DDD = (DDD, DDD)
EEE = (EEE, EEE)
GGG = (GGG, GGG)
ZZZ = (ZZZ, ZZZ)
""";

	private static final String SAMPLE_INPUT_2 = """
LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)
""";

	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(2, SAMPLE_INPUT_1),
			arguments(6, SAMPLE_INPUT_2),
			arguments(18113, TestUtil.getResourceAsString("AoC/input/2023/day08.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input)
	{
		assertThat(Day08.part1(input)).isEqualTo(expected);
	}

	@Test
	void parseInput() {
		val expectedWalk = List.of(true, false);
		val expectedGraph = Map.of(
			"AAA", new FunctionalUtil.Pair<>("BBB", "CCC"),
			"BBB", new FunctionalUtil.Pair<>("DDD", "EEE"),
			"CCC", new FunctionalUtil.Pair<>("ZZZ", "GGG"),
			"DDD", new FunctionalUtil.Pair<>("DDD", "DDD"),
			"EEE", new FunctionalUtil.Pair<>("EEE", "EEE"),
			"GGG", new FunctionalUtil.Pair<>("GGG", "GGG"),
			"ZZZ", new FunctionalUtil.Pair<>("ZZZ", "ZZZ")
		);
		val actual = Day08.parseInput(SAMPLE_INPUT_1);
		assertThat(actual.l()).isEqualTo(expectedWalk);
		assertThat(actual.r()).isEqualTo(expectedGraph);
	}
}
