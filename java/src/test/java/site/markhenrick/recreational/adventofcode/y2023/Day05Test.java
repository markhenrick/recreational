package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

	@ParameterizedTest
	@CsvSource({
			// soil, seed
			"0,0",
			"1,1",
			"48,48",
			"49,49",
			"52,50",
			"53,51",
			"98,96",
			"99,97",
			"50,98",
			"51,99",
			"81,79",
			"14,14",
			"57,55",
			"13,13",
	})
	void mapBackwards(int input, int expected) {
		val fn = Day05.parseFunction(SEED_SOIL_FUNCTION);
		assertThat(Day05.mapBackwards(fn, input)).isEqualTo(expected);
	}

	@Test
	void parseInput() {
		val actual = Day05.parseInput(SAMPLE_INPUT);
		assertThat(actual.l()).isEqualTo(List.of(79, 14, 55, 13));
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
