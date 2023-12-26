package site.markhenrick.recreational.rummikub;

import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static site.markhenrick.recreational.common.StringUtil.WORD_SPLITTER;

public class GameLogicTest {
	// TODO have all of these separate methods just be streams contributing to one parameter source

	@ParameterizedTest
	@ValueSource(strings = {
			"R1 R2 R3",
			"R4 R5 R6",
			"R11 R12 R13",
			"R2 R3 R4 R5",
			"R1 R2 R3 R4 R5 R6 R7 R8 R9 R10 R11 R12 R13",
			"R6 R7 R8 R9 R10 R11",
			"B1 B2 B3",
	})
	void validRuns(String inputRaw) {
		val input = parseInput(inputRaw);
		assertThat(GameLogic.validateSet(input)).isTrue();
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"R1",
			"R1 R2",
			"R1 B2 R3",
			"R1 B2 B3 B4",
			"R1 R2 R3 B3",
			"R1 R2 R3 B1 B2 B3",
			"R12 R13 R1",
			"R11 R12 R13 R1",
			"R1 R2 R3 R5 R6 R7",
			"R1 R2 R2 R3",
			"R3 R2 R1",
			"R1 R3 R2 R4",
	})
	void invalidRuns(String inputRaw) {
		val input = parseInput(inputRaw);
		assertThat(GameLogic.validateSet(input)).isFalse();
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"R1 G1 B1",
			"R1 G1 B1 Y1",
			"B1 Y1 R1",
			"R5 G5 B5",
	})
	void validGroups(String inputRaw) {
		val input = parseInput(inputRaw);
		assertThat(GameLogic.validateSet(input)).isTrue();

	}

	@ParameterizedTest
	@ValueSource(strings = {
			"R1",
			"R1 G1",
			"R1 G2 B1",
			"R1 G1 B2 Y1",
			"R1 G1 R1",
			"R1 G1 B1 R1",
			"R1 G1 B1 Y1 R1",
			"R1 G1 B1 Y1 R1 G1 B1 Y1"
	})
	void invalidGroups(String inputRaw) {
		val input = parseInput(inputRaw);
		assertThat(GameLogic.validateSet(input)).isFalse();
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"",
			"R1 B5 R7",
			"R0 R1 R2",
			"R12 R13 R14",
			"R0 G0 B0",
			"R14 G14 B14",
	})
	void invalidOthers(String inputRaw) {
		val input = parseInput(inputRaw);
		assertThat(GameLogic.validateSet(input)).isFalse();
	}

	private static List<Tile> parseInput(String input) {
		return WORD_SPLITTER.apply(input)
				.map(word -> {
					val suit = Tile.Suit.valueOf(word.substring(0, 1));
					val order = Integer.parseInt(word.substring(1));
					return new Tile(suit, order);
				})
				.toList();
	}
}
