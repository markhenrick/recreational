package site.markhenrick.recreational.adventofcode.y2021;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day04Test {
	private static final String SAMPLE_INPUT = "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1\n" +
		"\n" +
		"22 13 17 11  0\n" +
		" 8  2 23  4 24\n" +
		"21  9 14 16  7\n" +
		" 6 10  3 18  5\n" +
		" 1 12 20 15 19\n" +
		"\n" +
		" 3 15  0  2 22\n" +
		" 9 18 13 17  5\n" +
		"19  8  7 25 23\n" +
		"20 11 10 24  4\n" +
		"14 21 16 12  6\n" +
		"\n" +
		"14 21 17 24  4\n" +
		"10 16 15  9 19\n" +
		"18  8 23 26 20\n" +
		"22 11 13  6  5\n" +
		" 2  0 12  3  7";

	@Test
	void parse() {
		var parseResult = Day04.parse(SAMPLE_INPUT);

		var draw = parseResult.getValue0().toArray();
		assertThat(draw).hasSize(27);
		assertThat(draw[0]).isEqualTo(7);
		assertThat(draw[4]).isEqualTo(11);
		assertThat(draw[26]).isEqualTo(1);

		var boards = parseResult.getValue1();
		assertThat(boards.length).isEqualTo(3);
		var board0 = boards[0];
		assertThat(board0).hasDimensions(5, 5);
		assertThat(board0[0][0]).isEqualTo(22);
		assertThat(board0[0][4]).isEqualTo(0);
		assertThat(board0[4][0]).isEqualTo(1);
		assertThat(board0[4][4]).isEqualTo(19);
		assertThat(board0[2][2]).isEqualTo(14);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> play() {
		return Stream.of(
			arguments(4512, 1924, SAMPLE_INPUT),
			arguments(50008, 17408, TestUtil.getResourceAsString("AoC/input/2021/day04.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void play(int expectedFirstScore, int expectedLastScore, String input) {
		var parseResult = Day04.parse(input);
		var results = Day04.getAllResults(parseResult.getValue1(), parseResult.getValue0().iterator());
		assertThat(results.get(0)).isEqualTo(expectedFirstScore);
		assertThat(results.get(results.size() - 1)).isEqualTo(expectedLastScore);
	}
}
