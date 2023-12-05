package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day05Test {
	private static final String SAMPLE_INPUT = """
seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4
""";

	private static final String SEED_SOIL_FUNCTION = """
seed-to-soil map:
50 98 2
52 50 48
""";

	static Stream<Arguments> part1() {
		return Stream.of(
				arguments(35L, SAMPLE_INPUT),
				arguments(621354867L, TestUtil.getResourceAsString("AoC/input/2023/day05.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(long expected, String input)
	{
		assertThat(Day05.part1(input)).isEqualTo(expected);
	}

	static Stream<Arguments> part2() {
		return Stream.of(
				arguments(46L, SAMPLE_INPUT),
				arguments(15880236L, TestUtil.getResourceAsString("AoC/input/2023/day05.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(long expected, String input)
	{
		assertThat(Day05.part2(input)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
			// seed, location
			"79,82",
			"14,43",
			"55,86",
			"13,35",
	})
	void applyFns(int input, int expected) {
		val parsed = Day05.parseInputP1(SAMPLE_INPUT);
		assertThat(Day05.applyFns(parsed.r(), input)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
			// seed, soil
			"0,0",
			"1,1",
			"48,48",
			"49,49",
			"50,52",
			"51,53",
			"96,98",
			"97,99",
			"98,50",
			"99,51",
			"79,81",
			"14,14",
			"55,57",
			"13,13",
	})
	void applyFn(int input, int expected) {
		val fn = Day05.parseFunction(SEED_SOIL_FUNCTION);
		assertThat(Day05.applyFn(fn, input)).isEqualTo(expected);
	}

	// TODO tests for P2 parsing

	@Test
	void parseInputP1() {
		val actual = Day05.parseInputP1(SAMPLE_INPUT);
		assertThat(actual.l()).isEqualTo(List.of(79L, 14L, 55L, 13L));
		assertThat(actual.r()).hasSize(7);
		assertThat(actual.r().get(0).srcName()).isEqualTo("seed");
		assertThat(actual.r().get(1).srcName()).isEqualTo("soil");
		assertThat(actual.r().get(1).destName()).isEqualTo("fertilizer");
		assertThat(actual.r().get(6).destName()).isEqualTo("location");
	}

	@Test
	void parseFunction() {
		val expected = new Day05.PiecewiseFunction(
				"seed",
				"soil",
				List.of(
						new Day05.FunctionPiece(50, 98, 2),
						new Day05.FunctionPiece(52, 50, 48)
				)
		);
		assertThat(Day05.parseFunction(SEED_SOIL_FUNCTION)).isEqualTo(expected);
	}
}
